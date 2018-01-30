//package com.viewhigh.vadp.portal;
//
//import java.util.Properties;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.interceptor.TransactionInterceptor;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import com.viewhigh.vadp.framework.data.conf.EntityScan;
//import com.viewhigh.vadp.framework.data.conf.HbmScan;
//import com.viewhigh.vadp.framework.data.conf.ImportResource;
//import com.viewhigh.vadp.framework.data.conf.InitDataSourceConfig;
//import com.viewhigh.vadp.framework.data.trans.DynamicDataSourceSetting;
//@EnableTransactionManagement
////启注解事务管理， <tx:annotation-driven />
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
////@SpringBootApplication
////@ComponentScan("com.viewhigh.vadp")
//@ImportResource(mode = "classpath")
//@EntityScan(basePackages = "com.viewhigh")
//@HbmScan(classpaths = "com.viewhigh")
//@ComponentScan(basePackages = { "com.viewhigh.*" })
//public class PortalCoreApplication extends SpringBootServletInitializer {
//	private static final Logger logger = LoggerFactory.getLogger(PortalCoreApplication.class);
//
//    public static void main(String[] args) {
//        SpringApplication.run(PortalCoreApplication.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(PortalCoreApplication.class);
//    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedHeaders("*")
//                        .allowCredentials(true)
//                        .allowedMethods("*")
//                        .allowedOrigins("*");
//
//
//
//            }
//        };
//    }
//	private PlatformTransactionManager txManager1;
//
//	
//	// 创建事务管理器1
//	@Bean(name = "transactionManager")
//	public PlatformTransactionManager txManager() {
//		if (txManager1 == null) {
//			new InitDataSourceConfig(PortalCoreApplication.class); 
//			DynamicDataSourceSetting.setDataSourceKey("default");
//			txManager1 = new DynamicTransactionManagerForSpring();
//		}
//		return txManager1;
//	}
//	
////	@Override
////	public PlatformTransactionManager annotationDrivenTransactionManager() {
////		if (txManager1 == null) {
////			new InitDataSourceConfig(Application.class); 
////			DynamicDataSourceSetting.setDataSourceKey("default");
////			txManager1 = new DynamicTransactionManagerForSpring();
////		}
////		return txManager1;
////	}
//
//	@Bean
//	public Object testBean(PlatformTransactionManager platformTransactionManager) {
//		logger.info("当前事务管理器  >>>>>>>>>> {}", platformTransactionManager.getClass().getName());
//		return new Object();
//	}
//
//	/**
//	 * 定义事务的拦截器对应的方法拦截规则
//	 * @param platformTransactionManager
//	 * @return
//	 */
//	@Bean(name = "transactionInterceptor")
//	public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
//		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
//		// 事物管理器
//		transactionInterceptor.setTransactionManager(platformTransactionManager);
//		Properties transactionAttributes = new Properties();
//		transactionAttributes.setProperty("insert*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("update*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("save*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("edit*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("add*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("create*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("config*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("do*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("register*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("remove*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("start*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("pause*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("exchange*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("import*", "PROPAGATION_REQUIRED,-Throwable");
////		transactionAttributes.setProperty("test*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionAttributes.setProperty("get*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
//		transactionAttributes.setProperty("select*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
//		transactionAttributes.setProperty("find*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
//		transactionAttributes.setProperty("query*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
//		//为了支持手动控制事务，必须这么配置
//		transactionAttributes.setProperty("*", "PROPAGATION_SUPPORTS,-Throwable");
//
//		
////		transactionAttributes.setProperty("*", "PROPAGATION_REQUIRED,-Throwable");
//		transactionInterceptor.setTransactionAttributes(transactionAttributes);
//		return transactionInterceptor;
//	}
//	/**
//	 * 定义事务拦截器类对应的拦截规则
//	 * @return
//	 */
//	@Bean
//	public BeanNameAutoProxyCreator transactionAutoProxy() {
//		BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
//		transactionAutoProxy.setProxyTargetClass(true);
//		transactionAutoProxy.setBeanNames("*BO","*Bo","bo","*Service", "*ServiceImpl");
//		transactionAutoProxy.setInterceptorNames("transactionInterceptor");
//		return transactionAutoProxy;
//	}
//
//}
