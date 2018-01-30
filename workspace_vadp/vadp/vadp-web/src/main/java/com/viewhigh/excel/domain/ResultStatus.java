package com.viewhigh.excel.domain;

public enum ResultStatus {
	OK(1), PART_OK(2), UNSUPPORT_FILE(10001), PARAM_UNMATCH(10002), VALIDATE_FAIL(10003),
	CONNECTION_ERROR(10004), SQL_EXCEPTION(10005), SERVER_EXCEPTION(10006);
	private Integer value;

	ResultStatus(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
