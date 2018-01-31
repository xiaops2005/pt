package com.viewhigh.vadp.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * Spring 上下文获取工具类
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
@SuppressWarnings("all")
@Component
public class SpringContextUtil implements ApplicationContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);
	
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		logger.info("注入Spring Boot ApplicationContext... >>> {}", arg0);
		this.applicationContext = arg0;
	}

	public static <T> T getBean(String beanId) {
		return (T) applicationContext.getBean(beanId);
	}
}
