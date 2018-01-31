package com.viewhigh.vadp.framework.exception;
/**
 * 业务异常信息
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class ServiceException extends CoreException {

	private static final long serialVersionUID = 5159108974414693890L;

	public boolean isLogEnabled() {
		return true;
	}

	public ServiceException(String paramString, String[] paramArrayOfString) {
		super(paramString, paramArrayOfString);
	}

	public ServiceException(String paramString, Throwable paramThrowable, String[] paramArrayOfString) {
		super(paramString, paramThrowable, paramArrayOfString);
	}
}
