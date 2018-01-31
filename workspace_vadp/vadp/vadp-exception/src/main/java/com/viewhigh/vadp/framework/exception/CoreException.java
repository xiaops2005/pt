package com.viewhigh.vadp.framework.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 核心异常
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
@SuppressWarnings("all")
public class CoreException extends RuntimeException {

	protected static final Logger logger = LoggerFactory.getLogger(CoreException.class);
	
	private String code;
	private Object[] args;
	private String message;
	private Map attributeMap = new HashMap();

	
	public CoreException(String code, Object[] paramArrayOfObject) {
		this.code = code;
		this.args = paramArrayOfObject;
		logger.error(message);
	}

	public CoreException(String code, Throwable paramThrowable, Object[] paramArrayOfObject) {
		super(paramThrowable);
		this.code = code;
		this.args = paramArrayOfObject;
		logger.error(message);
	}

	public String getCode() {
		return this.code;
	}

	public Object[] getArgs() {
		return this.args;
	}

	@Override
	public String getMessage() {
		if (getExceptionMessage() != null) {
			return getCode() + ":" + getExceptionMessage();
		}
		return super.getMessage();
	}

	public String getExceptionMessage() {
		return this.message;
	}

}
