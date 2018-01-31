//package com.viewhigh.vadp.framework.data.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
///**
// * spring上下文助手类，该类不只是一份，目前冗余，未来需要合并
// * 版权所属：东软望海科技有限公司。
// * 作者：梁国华
// * 版本：V1.0
// * 创建日期：2017年4月25日
// * 修改日期: 2017年4月25日
// */
//public class SpringContextUtil implements ApplicationContextAware {
//	
//	private static final Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);
//	
//	private static ApplicationContext applicationContext;
//
//	@Override
//	public void setApplicationContext(ApplicationContext arg0)
//			throws BeansException {
//		logger.info("注入Spring Boot ApplicationContext... >>> {}", arg0);
//		this.applicationContext = arg0;
//	}
//
//	public static <T> T getBean(String beanId) {
//		return (T) applicationContext.getBean(beanId);
//	}
//}
