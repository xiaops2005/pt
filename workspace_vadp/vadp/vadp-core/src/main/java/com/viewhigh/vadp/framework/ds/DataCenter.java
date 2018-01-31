package com.viewhigh.vadp.framework.ds;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.viewhigh.vadp.framework.exception.CoreException;
/**
 * 数据中心
 * 	DataCenter是客户端和服务器端交换信息的载体，即消息单元。
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class DataCenter {

	private Header header = new Header();// 消息头
	private Body body = new Body();// 消息主题

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public static DataCenter parseCenter(String json) throws CoreException {
		JsonParser jp = new JsonParser();
		JsonObject jsonObj = jp.parse(json).getAsJsonObject();
		DataCenter center = new DataCenter();
		center.getHeader().parse(jsonObj); // 解析表头
		center.getBody().parse(jsonObj);// 解析请求内容
		return center;
	}

}
