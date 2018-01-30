package com.viewhigh.vadp.framework.web;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.viewhigh.vadp.framework.data.conf.EntityScan;
import com.viewhigh.vadp.framework.data.conf.HbmScan;
import com.viewhigh.vadp.framework.data.conf.ImportResource;
import com.viewhigh.vadp.framework.data.conf.InitDataSourceConfig;
import com.viewhigh.vadp.framework.data.cons.DataConfConst;
import com.viewhigh.vadp.framework.data.trans.DynamicDataSourceSetting;

import javax.servlet.MultipartConfigElement;

/**
 * SpringBoot启动类 
 * 版权所属：东软望海科技有限公司。 
 * 作者：刘晓平 版本：V1.0 
 * 创建日期： 2017年06月16日 
 * 修改日期:2017年06月16日
 */
@EnableTransactionManagement
// 启注解事务管理， <tx:annotation-driven />
@SpringBootApplication(exclude = {JpaRepositoriesAutoConfiguration.class,DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ImportResource(locations={"classpath:../../WEB-INF/conf/applicationContext-jdbc.xml"})
//拦截监听器等
@ServletComponentScan
//@EnableJpaRepositories("com.viewhigh.vadp")
//@EntityScan(basePackages = "com.viewhigh.vadp.*")
@ImportResource(mode = "web")
@EntityScan(basePackages = "com.viewhigh")
@HbmScan(classpaths = "com.viewhigh")
@ComponentScan(basePackages = { "com.viewhigh.*" })
//@Import({ DynamicDataSourceRegister.class })
// 注册动态多数据源
public class Application extends SpringBootServletInitializer //implements TransactionManagementConfigurer 
{

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private PlatformTransactionManager txManager1;

	
	// 创建事务管理器1
	@Bean(name = "transactionManager")
	public PlatformTransactionManager txManager() {
		if (txManager1 == null) {
			txManager1 = new DynamicTransactionManagerForSpring();
		}
		return txManager1;
	}
	
//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		if (txManager1 == null) {
//			new InitDataSourceConfig(Application.class); 
//			DynamicDataSourceSetting.setDataSourceKey("default");
//			txManager1 = new DynamicTransactionManagerForSpring();
//		}
//		return txManager1;
//	}

	@Bean
	public Object testBean(PlatformTransactionManager platformTransactionManager) {
		logger.info("当前事务管理器  >>>>>>>>>> {}", platformTransactionManager.getClass().getName());
		return new Object();
	}

	/**
	 * 定义事务的拦截器对应的方法拦截规则
	 * @param platformTransactionManager
	 * @return
	 */
	@Bean(name = "transactionInterceptor")
	public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		// 事物管理器
		transactionInterceptor.setTransactionManager(platformTransactionManager);
		Properties transactionAttributes = new Properties();
		String methodsEnable=DataConfConst.TRANSACTION_MAP.get(DataConfConst.TRANSACTION_BEAN_METHODS_ENABLE);
		String methodsReadonly=DataConfConst.TRANSACTION_MAP.get(DataConfConst.TRANSACTION_BEAN_METHODS_READONLY);
		String[] methods=methodsEnable.split(",");
		for(String method:methods)
		{
			transactionAttributes.setProperty(method.trim(), "PROPAGATION_REQUIRED,-Throwable");
		}
		methods=methodsReadonly.split(",");
		for(String method:methods)
		{
			transactionAttributes.setProperty(method.trim(), "PROPAGATION_REQUIRED,-Throwable,readOnly");
		}
		//为了支持手动控制事务，必须这么配置
		transactionAttributes.setProperty("*", "PROPAGATION_SUPPORTS,-Throwable");
		transactionInterceptor.setTransactionAttributes(transactionAttributes);
		return transactionInterceptor;
	}
	/**
	 * 定义事务拦截器类对应的拦截规则
	 * @return
	 */
	@Bean
	public BeanNameAutoProxyCreator transactionAutoProxy() {
		//初始化数据源配置
		new InitDataSourceConfig(Application.class); 
		DynamicDataSourceSetting.setDataSourceKey("default");
		
		BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
		transactionAutoProxy.setProxyTargetClass(true);
		String beanNames=DataConfConst.TRANSACTION_MAP.get(DataConfConst.TRANSACTION_BEAN_NAMES);
		transactionAutoProxy.setBeanNames(beanNames.trim().split(","));
		transactionAutoProxy.setInterceptorNames("transactionInterceptor");
		return transactionAutoProxy;
	}

	/**
	 * 
	 * 创建验证码
	 * @param: @return      
	 * @return: DefaultKaptcha      
	 * @throws IOException 
	 */
	@Bean
	public DefaultKaptcha captchaProducer() throws IOException {
		DefaultKaptcha kaptcha = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.load(new ClassPathResource("kaptcha.properties").getInputStream());
		Config config = new Config(properties);
		kaptcha.setConfig(config);
		return kaptcha;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedHeaders("*").allowCredentials(true).allowedMethods("*")
						.allowedOrigins("*");

			}
		};
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	/**
	 * Excel导入数据
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("8192KB");
		factory.setMaxRequestSize("8192KB");
		return factory.createMultipartConfig();
	}
//
//	@Bean
//	public SessionFactory sessionFactory() {
//		String dsName = DynamicDataSourceSetting.getDataSourceKey();
//		SessionFactory sessionFactory = DataConfConst.SESSION_FACTORY_MAP.get(dsName);
//		return sessionFactory;
//	}

}