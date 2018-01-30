package com.viewhigh.vadp.framework.common.controler.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.viewhigh.vadp.framework.ds.DataCenter;
import com.viewhigh.vadp.framework.ds.DataStore;
import com.viewhigh.vadp.framework.ds.Parameter;
import com.viewhigh.vadp.framework.exception.CoreException;
import com.viewhigh.vadp.framework.util.SpringContextUtil;
/**
 * 
 * 调用Service工具类
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
@SuppressWarnings("all")
public class CommonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	private static Map<Class,MethodAccess> accessMap = new HashMap<>();
	/**
	 * @Title: registerAccessMap   
	 * @Description: 注册服务到总控制器
	 * @param: @param bo
	 * @param: @param name      
	 * @return: void      
	 * @throws
	 */
	public static void registerAccessMap(Object bo,String name) {
		if(bo == null || accessMap.containsKey(bo.getClass())){
			return;
		}
		logger.info("注册[{}>{}]服务到总控制器", name, bo.getClass());
		accessMap.put(bo.getClass(), MethodAccess.get(bo.getClass()));
	}
	
	/**
	 * 注册转换器
	 */
	static{
		ConvertUtils.register(new DateConverter(), java.util.Date.class);
		logger.info("注册日期转换器");
	}

	/**
	 * 调用BO方法
	 * @throws Exception
	 */
	public static Object invokeBoMethod(DataCenter dataCenter,MultipartFile file) throws Exception {
		String boId = dataCenter.getBody().getParameters().getKeyValueStr(Parameter.key_boId);
		String methodName = dataCenter.getBody().getParameters().getKeyValueStr(Parameter.key_methodName);
		String[] methodParameterTypes = parseSplit(dataCenter.getBody().getParameters().getKeyValueStr(Parameter.key_methodParameterTypes)).toArray(new String[] {});
		
		if(file != null){
			methodParameterTypes = (String[]) ArrayUtils.add(methodParameterTypes, 0, "org.springframework.web.multipart.MultipartFile");
		}
		
		Object bo = SpringContextUtil.getBean(boId);
		
		MethodAccess access = null;
		
		if (accessMap.containsKey(bo.getClass())) {
			access = accessMap.get(bo.getClass());
		} else {
			access = MethodAccess.get(bo.getClass());
			accessMap.put(bo.getClass(), access);
		}
		
		
		Class[][] parameterTypes = access.getParameterTypes();
		
		Class[] paramTypes  = getMethodParameterTypes(methodParameterTypes, parameterTypes);
		
		Object invoke = access.invoke(bo, methodName,paramTypes,initParameters(dataCenter, methodParameterTypes, file).toArray());
		
		return invoke;
	}


	/**
	 * @Title: getMethodParameterTypes   
	 * @Description: 获取方法参数列表   
	 * @param: @param methodParameterTypes
	 * @param: @param parameterTypes
	 * @param: @return      
	 * @return: Class[]      
	 * @throws
	 */
	public static Class[] getMethodParameterTypes(String[] methodParameterTypes, Class[][] parameterTypes) {
		Class[] paramTypes = new Class[0];
		String[] paramArrayOfString = new String[methodParameterTypes.length];
		for (int i = 0; i < methodParameterTypes.length; i++) {
			String value = getPatternType(methodParameterTypes[i]);
			paramArrayOfString[i] = methodParameterTypes[i].replaceAll("<" + value + ">", "");
		}

		for (int j = 0; j < parameterTypes.length; j++) {
			Class<?>[] arrayOfClass = parameterTypes[j];
			if ((paramArrayOfString != null) || (arrayOfClass.length == 0)) {
				if ((paramArrayOfString == null) && (arrayOfClass.length == 0)) {
					paramTypes = arrayOfClass;
					break;
				}
				if ((paramArrayOfString != null) && (arrayOfClass.length == paramArrayOfString.length)
						&& (compareParametersType(paramArrayOfString, arrayOfClass))) {
					paramTypes = arrayOfClass;
					break;
				}
			}
		}
		return paramTypes;
	}
	
	
	
	/**
	 * 
	 * @Title: initParameters   
	 * @Description: 初始化调用Service方法所用到的参数
	 * @param: @param dataCenter 客户端请求数据
	 * @param: @param methodParameterTypes 参数类型名称列表
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: List      
	 * @throws
	 */
	public static List initParameters(DataCenter dataCenter,String[] methodParameterTypes,MultipartFile file) throws Exception {
		ArrayList localArrayList = new ArrayList();
		String parameters = dataCenter.getBody().getParameters().getKeyValueStr(Parameter.key_parameters);
		if(StringUtils.isBlank(parameters)){
			return localArrayList;
		}
		if(file != null){
			parameters = "file," + parameters;
		}
		
		List<String> paramNames = parseSplit(parameters);
		
		for (int i = 0; i < methodParameterTypes.length; i++) {
			String param = paramNames.get(i);
			
			Object value = dataCenter.getBody().getParameters().getKeyValue(param);
			if(value == null){
				value = dataCenter.getBody().getDataStores().get(param);
			}
			
			String pType = methodParameterTypes[i];
			// 放置基本值数据
			if (TypeKit.isPrimitiveType(pType)) {
				
				pushPrimitiveTypeValue(localArrayList, pType, value);
				
			}
			//上传类型
			else if("org.springframework.web.multipart.MultipartFile".equals(pType) || pType.contains("MultipartFile")){
				localArrayList.add(file);
			}else{
				Map map = null;
				DataStore body = null;
				if (value instanceof Map) {
					map = (Map) value;
				}
				if (value instanceof DataStore) {
					body = (DataStore) value;
					List<Object> primary = body.getRowSet().getPrimary();
					if (primary.size() > 0 && primary.get(0) instanceof Map) {
						map = (Map) primary.get(0);
					}
				}
				//放置Map数据
				if ("java.util.Map".equals(pType) || "Map".equals(pType)){
					localArrayList.add(map);
				}
				//放置List集合数据
				else if (StringUtils.contains(pType, "List") || "java.util.List".equals(pType)) {
					pushListPojo(localArrayList, getPatternType(pType), value);
				}
				//放置实体类数据
				else{
					setPojo(localArrayList, body, map);
				}
			}
		}
		return localArrayList;
	}

	/**
	 * 
	 * @Title: getPatternType   
	 * @Description: 获取参数列表中参数的具体类型  
	 * @param: @param value
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getPatternType(String value) {
		String result = "";
		Pattern pattern = Pattern.compile("(<(.*?)>)");
        Matcher matcher = pattern.matcher(value);
        while(matcher.find()){
            result = matcher.group(2);
            break;
        }
		return result;
	}
	
	/**
	 * 
	 * @Title: pushListPojo   
	 * @Description: 放置请求数据到实体类集合 
	 * @param: @param localArrayList
	 * @param: @param clsName
	 * @param: @param value
	 * @param: @throws CoreException
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	public static void pushListPojo(ArrayList localArrayList, String clsName, Object value) throws CoreException, Exception {
		if(value == null){
			localArrayList.add(null);
			return;
		}
		//基本类型
		if(TypeKit.isPrimitiveType(clsName)){
			List<Object> list = new LinkedList<Object>();
			String[] s = StringUtils.split(value + "", ","); 
			for (String v : s) {
				list.add(ConvertUtils.convert(v, TypeKit.getPrimitiveType(clsName)));
			}
			localArrayList.add(list);
			return;
		}
		//非基本类型封装
		if (!(value instanceof DataStore)) {
			return;
		}
		DataStore body = (DataStore) value;
		List<Object> primary = body.getRowSet().getPrimary();
		//封装到Map
		if(StringUtils.isBlank(body.getRowSetName())){
			localArrayList.add(primary);
		}
		//封装到实体类
		else{
			ArrayList<Object> sTmp = new ArrayList<Object>();
			for (Object object : primary) {
				setPojo(sTmp, body, object);
			}
			localArrayList.add(sTmp);
		}
		
	}
	
	/**
	 * 
	 * @Title: setPojo   
	 * @Description: 放置请求数据到实体类 
	 * @param: @param localArrayList
	 * @param: @param body
	 * @param: @param object
	 * @return: void      
	 * @throws
	 */
	public static void setPojo(ArrayList localArrayList,DataStore body, Object object) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
		Object newObj = (Object) Class.forName(body.getRowSetName()).newInstance();
		List<String> converList = new ArrayList<>();
		createFieldObjFirst(newObj, converList);
		Map<String, Object> properties = (Map) object;
		for (String name : converList) {
			if(properties != null && properties.containsKey(name)){
				Object cObject = properties.get(name);
				if (cObject instanceof HashMap) {
					HashMap<String, Object> value = (HashMap<String, Object>) cObject;
					Iterator<Entry<String, Object>> iterator = value.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iterator.next();
						if(entry.getValue() != null && !(entry.getValue() instanceof HashMap)){
							properties.put(name + "." + entry.getKey(), entry.getValue());
						}
					}
				}
				properties.remove(name);
			}	
		}
		BeanUtils.populate(newObj, properties);
		localArrayList.add(newObj);
	}

	/**
	 * 
	 * @Title: createFieldObjFirst   
	 * @Description: 实例化 实体类中未实例化的属性 
	 * @param: @param newObj
	 * @param: @param converList
	 * @return: void      
	 * @throws
	 */
	public static void createFieldObjFirst(Object newObj,List<String> converList) throws IllegalAccessException, InvocationTargetException,NoSuchMethodException, InstantiationException, ClassNotFoundException {
		java.lang.reflect.Field[] fields = newObj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			if (PropertyUtils.isReadable(newObj, name) && PropertyUtils.isWriteable(newObj, name)) {
				if (!TypeKit.isPrimitiveType(fields[i].getType()) && BeanUtils.getProperty(newObj, name) == null) {
					Object newInstance = Class.forName(fields[i].getType().getName()).newInstance();
					converList.add(name);
					BeanUtils.setProperty(newObj, name, newInstance);
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @Title: pushPrimitiveTypeValue   
	 * @Description: 放置请求数据到基本类型
	 * @param: @param localArrayList
	 * @param: @param cls
	 * @param: @param value
	 * @return: void      
	 * @throws
	 */
	public static void pushPrimitiveTypeValue(ArrayList localArrayList, String cls, Object value) throws CoreException, Exception {
		if (value == null) {
			localArrayList.add(null);
			return;
		}
		if (StringUtils.equalsIgnoreCase(cls, String.class.getName())) {
			localArrayList.add(value + "");
		}else{
			localArrayList.add(ConvertUtils.convert(value + "", TypeKit.getPrimitiveType(cls)));
		}
	}
	
	/**
	 * 获取字符串方法中的类型名称集合
	 * @param methodParameterTypes
	 * @return
	 */
	public static List<String> parseSplit(String methodParameterTypes){
		if (StringUtils.isBlank(methodParameterTypes)) {
			return new LinkedList<>();
		}
		List<String> list = new LinkedList<String>();
		String[] split = methodParameterTypes.split(",(?![^()]*+\\))", -1);
		for (String val : split) {
			if(StringUtils.isBlank(val)){
				continue;
			}
			list.add(val);
		}
		return list;
	}


	/**
	 * 获取方法返回类型
	 * @param bo
	 * @param methodName
	 * @return
	 */
	public static Type getMethodType(Object bo, String methodName) {
		Type type = null;
		Method[] methods = bo.getClass().getMethods();
		// 遍历方法
		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				// 获取返回类型
				type = m.getGenericReturnType();
			}
		}
		return type;
	}

	
	/**
	 * 
	 * @Title: compareParametersType   
	 * @Description: 判断参数类型  
	 * @param: @param paramArrayOfString
	 * @param: @param paramArrayOfClass
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean compareParametersType(String[] paramArrayOfString, Class<?>[] paramArrayOfClass) {
		int i = 0;
		for (; i < paramArrayOfClass.length; i++) {
			if (paramArrayOfString[i] == null) {
				return false;
			}
			String str = paramArrayOfString[i].trim();
			if ((!paramArrayOfClass[i].getName().equals(str)) && ((!str.equals("Integer")) || (!paramArrayOfClass[i].equals(Integer.class)))
					&& ((!str.equals("Float")) || (!paramArrayOfClass[i].equals(Float.class))) && ((!str.equals("Double")) || (!paramArrayOfClass[i].equals(Double.class)))
					&& ((!str.equals("Boolean")) || (!paramArrayOfClass[i].equals(Boolean.class))) && ((!str.equals("Byte")) || (!paramArrayOfClass[i].equals(Byte.class)))
					&& ((!str.equals("Character")) || (!paramArrayOfClass[i].equals(Character.class))) && ((!str.equals("Long")) || (!paramArrayOfClass[i].equals(Long.class)))
					&& ((!str.equals("Short")) || (!paramArrayOfClass[i].equals(Short.class))) && ((!str.equals("String")) || (!paramArrayOfClass[i].equals(String.class)))
					&& ((!str.equals("Object")) || (!paramArrayOfClass[i].equals(Object.class)))) {
				break;
			}
		}
		if (i == paramArrayOfClass.length) {
			return true;
		}
		return false;
	}
}
