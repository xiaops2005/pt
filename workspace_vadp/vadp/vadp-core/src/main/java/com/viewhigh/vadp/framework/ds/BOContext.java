package com.viewhigh.vadp.framework.ds;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.viewhigh.vadp.framework.exception.CoreException;
/**
 * 
 * 多返回值类型实体类
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class BOContext  {

	public Map<String,Object> datas = new HashMap<>();
	
	/**
	 * 
	 * @Title: set   
	 * 放置  
	 * @param: @param key
	 * @param: @param value
	 * @param: @return
	 * @param: @throws CoreException      
	 * @return: boolean      
	 * @throws
	 */
	public boolean set(String key, Object value) throws CoreException {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		if(value != null && value instanceof BOContext){
			throw new CoreException("12022", new String[] { key + ":" + value.toString() });
		}
		remove(key);
		datas.put(key, value);
		return true;
	}
	/**
	 * 
	 * @Title: remove   
	 * @Description:删除   
	 * @param: @param key      
	 * @return: void      
	 * @throws
	 */
	public void remove(String key){
		if (StringUtils.isBlank(key)) {
			return;
		}
		if(datas.containsKey(key)){
			datas.remove(key);
		}
	}
	/**
	 * 
	 * @Title: get   
	 * @Description: 获取
	 * @param: @param key
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	public Object get(String key){
		if (StringUtils.isBlank(key)) {
			return null;
		}
		if(!datas.containsKey(key)){
			return null;
		}
		return datas.get(key);
	}
	
	/**
	 * 
	 * @Title: getValue   
	 * @Description: 获取全部数据 
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String,Object> getValue(){
		return datas;
	}
	
}
