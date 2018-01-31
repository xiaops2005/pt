package com.viewhigh.vadp.framework.base.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viewhigh.vadp.framework.base.IBaseService;
/**
 * BaseService实现类BaseServiceImpl
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
@Service
@Transactional
public class BaseServiceImpl implements IBaseService {

	protected static final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);;

	/**
	 * @Title: saveErrorLog   
	 * @Description: 保存错误日志   
	 * @param: @param msg 错误信息描述  
	 * @return: void      
	 * @throws
	 */
	@Override
	public void saveErrorLog(String msg) {
		log.error(msg);
	}
	
	/**
	 * 
	 * @Title: saveWarnLog   
	 * @Description: 保存警告信息
	 * @param: @param msg 警告信息描述  
	 * @return: void      
	 * @throws
	 */
	@Override
	public void saveWarnLog(String msg) {
		log.warn(msg);
	}
	
	/**
	 * 
	 * @Title: saveDebugLog   
	 * @Description: 保存调试信息   
	 * @param: @param msg 调试信息描述  
	 * @return: void      
	 * @throws
	 */
	@Override
	public void saveDebugLog(String msg) {
		log.debug(msg);
	}

	/**
	 * 
	 * @Title: saveInfoLog   
	 * @Description: 保存日志信息   
	 * @param: @param msg 描述     
	 * @return: void      
	 * @throws
	 */
	@Override
	public void saveInfoLog(String msg) {
		log.info(msg);
	}

	/**
	 * 
	 * @Title: saveErrorLog   
	 * @Description: 保存错误日志
	 * @param: @param msg 错误信息描述
	 * @param: @param e  错误异常
	 * @return: void      
	 * @throws
	 */
	@Override
	public void saveErrorLog(String msg, Exception e) {
		log.error(msg, e);
	}
	
	/**
	 * 
	 * @Title: saveWarnLog   
	 * @Description: 保存警告信息   
	 * @param: @param msg 警告信息描述
	 * @param: @param e   警告异常
	 * @return: void      
	 * @throws
	 */
	@Override
	public void saveWarnLog(String msg, Exception e) {
		log.warn(msg, e);
	}
	
	/**
	 * 
	 * @Title: saveDebugLog   
	 * @Description: 保存调试信息
	 * @param: @param msg 调试信息描述
	 * @param: @param e   调试异常
	 * @return: void      
	 * @throws
	 */
	@Override
	public void saveDebugLog(String msg, Exception e) {
		log.debug(msg, e);
	}
	
	/**
	 * 
	 * @Title: saveInfoLog   
	 * @Description: 保存日志信息
	 * @param: @param msg 日志信息描述
	 * @param: @param e   日志异常
	 * @return: void      
	 * @throws
	 */
	@Override
	public void saveInfoLog(String msg, Exception e) {
		log.info(msg, e);
	}

}
