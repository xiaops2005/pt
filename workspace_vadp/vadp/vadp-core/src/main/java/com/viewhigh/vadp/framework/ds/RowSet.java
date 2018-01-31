package com.viewhigh.vadp.framework.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.viewhigh.vadp.framework.exception.CoreException;
/**
 * RowSet是客户端数据的容器
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class RowSet {

	private List<Object> primary = new ArrayList<Object>();

	public List<Object> getPrimary() {
		return primary;
	}

	public void setPrimary(List<Object> primary) {
		this.primary = primary;
	}

	public void parse(JsonObject jsonObj) throws CoreException {
		if (jsonObj.has("primary") && jsonObj.get("primary").isJsonArray()) {
			JsonArray primaryArray = jsonObj.get("primary").getAsJsonArray();
			pushValue(primaryArray, this.primary);
		}
	}

	private void pushValue(JsonArray array, List<Object> list) {
		Iterator<JsonElement> iterator = array.iterator();
		while (iterator.hasNext()) {
			JsonElement elt = (JsonElement) iterator.next();
			if (!elt.isJsonObject()) {
				continue;
			}
			Map<String, Object> value = new HashMap<String, Object>();
			Iterator<Entry<String, JsonElement>> objIter = elt.getAsJsonObject().entrySet().iterator();
			while (objIter.hasNext()) {
				Map.Entry<String, JsonElement> entry = (Map.Entry<String, JsonElement>) objIter.next();
				if (entry.getValue().isJsonNull()) {
					continue;
				}
				if (entry.getValue().isJsonObject()) {
					parseJsonObj(entry.getKey(), entry.getValue().getAsJsonObject(), value);
				} else if (entry.getValue().isJsonArray()) {
					pushValue(array, list);
				} else {
					value.put(entry.getKey(), entry.getValue().getAsString());
				}
			}
			list.add(value);
		}
	}

	private void parseJsonObj(String key, JsonObject jsonObject, Map<String, Object> value) {
		Map<String, Object> datas = new HashMap<String, Object>();
		Iterator<java.util.Map.Entry<String, JsonElement>> iterator = jsonObject.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, JsonElement> entry = (Map.Entry<String, JsonElement>) iterator.next();
			if (entry.getValue().isJsonNull()) {
				datas.put(entry.getKey(), null);
				continue;
			}
			if (!entry.getValue().isJsonObject()) {
				datas.put(entry.getKey(), entry.getValue().getAsString());
			} else {
				parseJsonObj(entry.getKey(), entry.getValue().getAsJsonObject(), datas);
			}
		}
		value.put(key, datas);
	}

	public void insertRowData(int paramInt, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		this.getPrimary().add(paramInt, paramMap);
	}

	public int addRowData(Map paramMap) {
		this.getPrimary().add(paramMap);
		return this.getPrimary().size() - 1;
	}

	public void clear() {
		this.getPrimary().clear();
	}

	public void deleteRow(int paramInt){
		if (this.getPrimary().size() == 0 || paramInt > (this.getPrimary().size() - 1)) {
			return;
		}
		this.getPrimary().remove(paramInt);
	}

	public  Object getRow(int paramInt){
		return this.getPrimary().get(paramInt);
	}

	public int getRowCount() {
		return this.getPrimary().size();
	}
}
