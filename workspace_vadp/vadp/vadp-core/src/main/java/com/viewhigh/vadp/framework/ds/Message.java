package com.viewhigh.vadp.framework.ds;

import com.google.gson.JsonObject;
import com.viewhigh.vadp.framework.exception.CoreException;
/**
 * 状态信息
 * 	状态信息包括 ：状态标题、状态详细信息
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class Message {

	private String title = "";
	private String detail = "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void parse(JsonObject jsonObj) throws CoreException {
		if(!jsonObj.has("title") || !jsonObj.has("detail")){
			throw new CoreException("12021", new String[] { "title or detail" });
		}
		this.title = jsonObj.get("title").getAsString();
		this.detail = jsonObj.get("detail").getAsString();
	}

}
