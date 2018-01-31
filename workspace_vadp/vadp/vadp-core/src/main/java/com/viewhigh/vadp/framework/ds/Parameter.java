package com.viewhigh.vadp.framework.ds;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.viewhigh.vadp.framework.exception.CoreException;
/**
 * 参数列表
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class Parameter extends HashMap<String, Object> {
	
	public static final String key_parameters = "_parameters";// 调用的参数名称
	public static final String key_parameterTypes = "_parameterTypes";// 调用的参数类型
	public static final String key_dcID = "dcID";// 调用哪个模块
	public static final String key_boId = "_boId";// 调用哪个service
	public static final String key_methodName="_methodName";//调用service中的哪个方法
	public static final String key_methodParameterTypes = "_methodParameterTypes";// 调用方法参数列表类型
	public static final String key_nom = "nom";
	public static final String key_result = "result";// 返回结果
	
	public Object getKeyValue(String key) {
		if (this.containsKey(key)) {
			return this.get(key);
		}
		return null;
	}
	
	public String getKeyValueStr(String key) {
		if (this.containsKey(key)) {
			return this.get(key) + "";
		}
		return null;
	}
	
	
	private Map<String, Object> maps = new HashMap<String, Object>();

	public Map<String, Object> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}
	
	public void parse(JsonObject jsonObj) throws CoreException {
		Iterator<java.util.Map.Entry<String, JsonElement>> iterator = jsonObj.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String,JsonElement> entry = (Map.Entry<String,JsonElement>) iterator.next();
			if(entry.getValue().isJsonNull()){
				this.put(entry.getKey(), null);
				continue;
			}
			if(!entry.getValue().isJsonObject()){
				this.put(entry.getKey(), entry.getValue().getAsString());
			}else{
				parseJsonObj(entry.getKey(), entry.getValue().getAsJsonObject());
			}
		}
	}
	
	private void parseJsonObj(String key, JsonObject jsonObject) {
		Map<String,String> datas = new HashMap<String, String>();
		Iterator<java.util.Map.Entry<String, JsonElement>> iterator = jsonObject.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String,JsonElement> entry = (Map.Entry<String,JsonElement>) iterator.next();
			if(entry.getValue().isJsonNull()){
				datas.put(entry.getKey(), null);
				continue;
			}
			if(!entry.getValue().isJsonObject()){
				datas.put(entry.getKey(), entry.getValue().getAsString());
			}else{
				parseJsonObj(entry.getKey(), entry.getValue().getAsJsonObject());
			}
		}
		this.put(key, datas);
	}
	
	
}
