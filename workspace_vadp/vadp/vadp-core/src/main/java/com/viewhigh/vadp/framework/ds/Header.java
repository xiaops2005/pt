package com.viewhigh.vadp.framework.ds;

import com.google.gson.JsonObject;
import com.viewhigh.vadp.framework.exception.CoreException;
/**
 * 消息头
 * 	 header消息头,包含服务器端响应的状态信息,如状态码、状态标题、状态详细信息
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class Header {

	private Integer code = 0;
	private Message message = new Message();

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void parse(JsonObject jsonObj) throws CoreException {
		// 验证数据格式是否正常
		if (!jsonObj.has("header")) {
			throw new CoreException("12021", new String[] { "header" });
		}
		JsonObject jsonHeader = jsonObj.getAsJsonObject("header");
		if (!jsonHeader.has("code") || !jsonHeader.has("message") || !jsonHeader.get("message").isJsonObject()) {
			throw new CoreException("12021", new String[] { " code or message !" });
		}
		this.code = jsonHeader.get("code").getAsInt();
		JsonObject message = jsonHeader.getAsJsonObject("message");
		this.getMessage().parse(message);
	}

}
