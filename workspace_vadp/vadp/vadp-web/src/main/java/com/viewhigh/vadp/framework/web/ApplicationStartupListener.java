//package com.viewhigh.vadp.framework.web;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.viewhigh.vadp.framework.common.controler.util.CommonUtil;
///**
// * 监听SpringBoot启动
// * 版权所属：东软望海科技有限公司。
// * 作者：刘晓平
// * 版本：V1.0
// * 创建日期：  2017年06月22日
// * 修改日期: 2017年06月22日
// */
//@Component
//public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {
//
//	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);
//
//	/**
//	 * 启动后执行方法
//	 * 注册服务到控制器
//	 */
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		ApplicationContext ctx = event.getApplicationContext();
//		String[] beanNames =  ctx.getBeanNamesForAnnotation(Service.class);
//		if(beanNames == null){
//			return;
//		}
//		logger.info("springBoot启动成功,开始注册服务到控制器");
//		for (String bname : beanNames) {
//			Object bo = ctx.getBean(bname);
//			CommonUtil.registerAccessMap(bo, bname);
//		}
//		logger.info("springBoot启动成功,结束注册服务到控制器");
//	}
//
//}
