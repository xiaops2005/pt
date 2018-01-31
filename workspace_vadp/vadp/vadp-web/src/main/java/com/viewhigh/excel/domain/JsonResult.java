package com.viewhigh.excel.domain;

import com.google.common.collect.Lists;

import java.util.List;

public class JsonResult {
	private int status;
	private String message;
	private List<? extends Base> result;

	public JsonResult() {
	}

	public JsonResult(Integer status, String message, List<? extends Base> result) {
		this.status = status;
		this.message = message;
		this.result = result;
	}

	public JsonResult(Integer status, String message) {
		this.status = status;
		this.message = message;
		this.result = Lists.newArrayList();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<? extends Base> getResult() {
		return result;
	}

	public void setResult(List<? extends Base> result) {
		this.result = result;
	}
}
