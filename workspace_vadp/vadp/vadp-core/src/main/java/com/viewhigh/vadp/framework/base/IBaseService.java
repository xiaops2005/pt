package com.viewhigh.vadp.framework.base;
/**
 * BaseService接口
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public interface IBaseService {
	
	/**
	 * @Title: saveErrorLog   
	 * @Description: 保存错误日志   
	 * @param: @param msg 错误信息描述  
	 * @return: void      
	 * @throws
	 */
	public void saveErrorLog(String msg);
	/**
	 * 
	 * @Title: saveErrorLog   
	 * @Description: 保存错误日志
	 * @param: @param msg 错误信息描述
	 * @param: @param e  错误异常
	 * @return: void      
	 * @throws
	 */
	public void saveErrorLog(String msg,Exception e);
	/**
	 * 
	 * @Title: saveWarnLog   
	 * @Description: 保存警告信息
	 * @param: @param msg 警告信息描述  
	 * @return: void      
	 * @throws
	 */
	public void saveWarnLog(String msg);
	/**
	 * 
	 * @Title: saveWarnLog   
	 * @Description: 保存警告信息   
	 * @param: @param msg 警告信息描述
	 * @param: @param e   警告异常
	 * @return: void      
	 * @throws
	 */
	public void saveWarnLog(String msg,Exception e);
	/**
	 * 
	 * @Title: saveDebugLog   
	 * @Description: 保存调试信息   
	 * @param: @param msg 调试信息描述  
	 * @return: void      
	 * @throws
	 */
	public void saveDebugLog(String msg);
	/**
	 * 
	 * @Title: saveDebugLog   
	 * @Description: 保存调试信息
	 * @param: @param msg 调试信息描述
	 * @param: @param e   调试异常
	 * @return: void      
	 * @throws
	 */
	public void saveDebugLog(String msg,Exception e);
	/**
	 * 
	 * @Title: saveInfoLog   
	 * @Description: 保存日志信息   
	 * @param: @param msg 描述     
	 * @return: void      
	 * @throws
	 */
	public void saveInfoLog(String msg);
	/**
	 * 
	 * @Title: saveInfoLog   
	 * @Description: 保存日志信息
	 * @param: @param msg 日志信息描述
	 * @param: @param e   日志异常
	 * @return: void      
	 * @throws
	 */
	public void saveInfoLog(String msg,Exception e);
	
}
