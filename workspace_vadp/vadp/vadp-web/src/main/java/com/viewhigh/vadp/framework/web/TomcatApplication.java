package com.viewhigh.vadp.framework.web;
//package com.viewhigh.vadp.framework;
//
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.web.SpringBootServletInitializer;
//import org.springframework.context.annotation.Bean;
///**
// * SpringBoot 启动入口 (必须放到想的根目录下)
// * @author lxp
// */
//@SpringBootApplication
//public class TomcatApplication extends SpringBootServletInitializer {
//
//	private static Log logger = LogFactory.getLog(TomcatApplication.class);
//	
//	static{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("系统启动时间：" + sdf.format(new Date()));
//	}
//	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("系统启动时间：" + sdf.format(new Date()));
//		return application.sources(TomcatApplication.class);
//	}
//
//	@Bean
//	protected ServletContextListener listener() {
//		return new ServletContextListener() {
//
//			@Override
//			public void contextInitialized(ServletContextEvent sce) {
//				logger.info("ServletContext initialized");
//			}
//
//			@Override
//			public void contextDestroyed(ServletContextEvent sce) {
//				logger.info("ServletContext destroyed");
//			}
//
//		};
//	}
//
//	public static void main(String[] args) throws Exception {
//		SpringApplication.run(TomcatApplication.class, args);
//	}
//
//}