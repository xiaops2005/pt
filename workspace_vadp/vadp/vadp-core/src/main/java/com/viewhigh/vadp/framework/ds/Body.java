package com.viewhigh.vadp.framework.ds;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.viewhigh.vadp.framework.exception.CoreException;
/**
 * 
 * 请求数据主体
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class Body {

	private Parameter parameters = new Parameter();// 参数列表
	private HashMap<String, DataStore> dataStores = new HashMap<String, DataStore>();// 返回的内容

	public Parameter getParameters() {
		return parameters;
	}

	public void setParameters(Parameter parameters) {
		this.parameters = parameters;
	}

	public HashMap<String, DataStore> getDataStores() {
		return dataStores;
	}

	public void setDataStores(HashMap<String, DataStore> dataStores) {
		this.dataStores = dataStores;
	}
	
	public void parse(JsonObject jsonObj) throws CoreException {
		if (!jsonObj.has("body")) {
			throw new CoreException("12021", new String[]{ "body" });
		}
		JsonObject body = jsonObj.getAsJsonObject("body");
		if(body.has("parameters")){
			this.getParameters().parse(body.getAsJsonObject("parameters"));
		}
		if(!body.has("dataStores")){
			throw new CoreException("12021", new String[] { "dataStores" });
		}
		JsonObject dataStoresJson = body.getAsJsonObject("dataStores");
		this.parseDataStore(dataStoresJson);
	}
	
	public void parseDataStore(JsonObject jsonObj) throws CoreException {
		Iterator<java.util.Map.Entry<String, JsonElement>> iterator = jsonObj.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, JsonElement> entry = (Map.Entry<String, JsonElement>) iterator.next();
			DataStore responseBody = new DataStore();
			responseBody.parse(entry.getValue().getAsJsonObject());
			this.dataStores.put(entry.getKey(), responseBody);
		}
	}

}
