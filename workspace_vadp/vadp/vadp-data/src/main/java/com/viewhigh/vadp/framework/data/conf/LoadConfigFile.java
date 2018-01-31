package com.viewhigh.vadp.framework.data.conf;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.DefaultComponentSafeNamingStrategy;
import org.hibernate.cfg.DefaultNamingStrategy;
import org.hibernate.cfg.EJB3NamingStrategy;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viewhigh.vadp.framework.data.cons.DataConfConst;
import com.viewhigh.vadp.framework.data.exception.DataException;
import com.viewhigh.vadp.framework.data.strategy.VADPImprovedNamingStrategy;

/**
 * 加载数据源配置文件
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class LoadConfigFile {

	private static final String CONF_FILE_DATASOURCE_NAME = "vadp-data.properties";
	private static final Logger logger = LoggerFactory.getLogger(LoadConfigFile.class);
	/**
	 * web方式加载配置文件
	 */
	public void loadByWeb() {
		//取得classpath
		String fullPath = this.getClass().getResource("").getPath();
		String webInfPath = null;
		//获得WEB-INF/conf目录路径
		File componentrefFile = null;
		int libpath = fullPath.lastIndexOf("lib");
		if (libpath > 0) {
			webInfPath = fullPath.substring(0, libpath - 1);
			if (webInfPath.startsWith("file")) {
				webInfPath = webInfPath.substring(5);
			}
			componentrefFile = new File(webInfPath + "/conf/"+CONF_FILE_DATASOURCE_NAME);
		}
		int classespath = fullPath.lastIndexOf("classes");
		if (libpath < 0 && webInfPath == null) {
			webInfPath = fullPath.substring(0, classespath - 1);
			componentrefFile = new File(webInfPath + "/conf/"+CONF_FILE_DATASOURCE_NAME);
		}
		Properties pro = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(componentrefFile);
			pro.load(in);
		} catch (Exception e) {
			logger.error("读取资源文件错误！！！"+componentrefFile);
			throw new DataException("13001",e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				logger.error("读取资源文件错误！！！"+componentrefFile);
				throw new DataException("13001",e);
			}
		}
		parseConf(pro);
		logger.info("解析数据源配置文件"+componentrefFile.getAbsolutePath()+"结束");
	}
	/**
	 * classpath方式加载资源文件
	 */
	public void loadByClassPath() {
		//取得classpath
		String fullPath = this.getClass().getResource("/"+CONF_FILE_DATASOURCE_NAME).getPath();
		Properties pro = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(fullPath);
			pro.load(in);
		} catch (Exception e) {
			logger.error("读取资源文件错误！！！"+fullPath);
			throw new DataException("13001",e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				logger.error("读取资源文件错误！！！"+fullPath);
				throw new DataException("13001",e);
			}
		}
		parseConf(pro);
		Object o=DataConfConst.SESSION_FACTORY_MAP;
		logger.info("解析数据源配置文件"+fullPath+"结束");
	}
	/**
	 * 解析资源文件
	 * @param pro
	 */
	private void parseConf(Properties pro)
	{
		//加载默认数据源
		if(!StringUtils.isEmpty(pro.getProperty(DataConfConst.DATASOURCE_PRIFIX+"."+DataConfConst.DEFAULT_DATASOURCE_PRIFIX+"."+DataConfConst.DRIVER).trim()))
		{
			getSingleConf(pro,DataConfConst.DEFAULT_DATASOURCE_PRIFIX);
		}
		else
		{
			//抛出“缺少默认数据源的异常”
			throw new DataException("13002",null);
		}
		if(!StringUtils.isEmpty(pro.getProperty(DataConfConst.CUSTOM_DATASOURCE_NAMES)))
		{
			String names=pro.getProperty(DataConfConst.CUSTOM_DATASOURCE_NAMES).trim();
			String[] nameArray=names.split(",");
			for(String name:nameArray)
			{
				getSingleConf(pro,name);
			}
		}
		//取出事务设置信息
		DataConfConst.TRANSACTION_MAP.put(DataConfConst.TRANSACTION_BEAN_NAMES, pro.getProperty(DataConfConst.TRANSACTION_BEAN_NAMES));
		DataConfConst.TRANSACTION_MAP.put(DataConfConst.TRANSACTION_BEAN_METHODS_ENABLE, pro.getProperty(DataConfConst.TRANSACTION_BEAN_METHODS_ENABLE));
		DataConfConst.TRANSACTION_MAP.put(DataConfConst.TRANSACTION_BEAN_METHODS_READONLY, pro.getProperty(DataConfConst.TRANSACTION_BEAN_METHODS_READONLY));
	}
	/**
	 * 根据数据源名称取得单个数据源的配置
	 * @param pro
	 * @param prefix
	 */
	@SuppressWarnings("deprecation")
	private void getSingleConf(Properties pro,String dataSourceName)
	{
		String dataPrefix=DataConfConst.DATASOURCE_PRIFIX+"."+dataSourceName+".";
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.setProperty(DataConfConst.DRIVER, pro.getProperty(dataPrefix+DataConfConst.DRIVER));
		config.setProperty(DataConfConst.USER, pro.getProperty(dataPrefix+DataConfConst.USER));
		config.setProperty(DataConfConst.PASS, pro.getProperty(dataPrefix+DataConfConst.PASS));
		config.setProperty(DataConfConst.POOL_SIZE, pro.getProperty(dataPrefix+DataConfConst.POOL_SIZE));
		config.setProperty(DataConfConst.URL, pro.getProperty(dataPrefix+DataConfConst.URL));
		config.setProperty(DataConfConst.SHOW_SQL, pro.getProperty(dataPrefix+DataConfConst.SHOW_SQL));
		config.setProperty(DataConfConst.FORMAT_SQL, pro.getProperty(dataPrefix+DataConfConst.FORMAT_SQL));
		config.setProperty(DataConfConst.DIALECT, pro.getProperty(dataPrefix+DataConfConst.DIALECT));
		config.setProperty(DataConfConst.AUTOCOMMIT, pro.getProperty(dataPrefix+DataConfConst.AUTOCOMMIT));
		config.setProperty(DataConfConst.CURRENT_SESSION_CONTEXT_CLASS, pro.getProperty(dataPrefix+DataConfConst.CURRENT_SESSION_CONTEXT_CLASS));
		config.setProperty(DataConfConst.CONNECTION_PROVODER, pro.getProperty(dataPrefix+DataConfConst.CONNECTION_PROVODER));
		config.setProperty(DataConfConst.DATASOURCE, pro.getProperty(dataPrefix+DataConfConst.DATASOURCE));
		config.setNamingStrategy(DefaultNamingStrategy.INSTANCE);
		//加载注解方式的实体映射
		Iterator<String> classPaths=DataConfConst.ENTITY_ANNOTATION_MAP.keySet().iterator();
		String classpath=null;
		while(classPaths.hasNext())
		{
			classpath=classPaths.next();
			config.addAnnotatedClass(DataConfConst.ENTITY_ANNOTATION_MAP.get(classpath));
		}
		//加载classpath下面的hbm方式的实体映射
		Iterator<String> classPathHbms=DataConfConst.ENTITY_CLASSPATH_HBM_MAP.keySet().iterator();
		String classPathHbm=null;
		while(classPathHbms.hasNext())
		{
			classPathHbm=classPathHbms.next();
			config.addResource(DataConfConst.ENTITY_CLASSPATH_HBM_MAP.get(classPathHbm));
		}
		
		//加载WEB/INF下面的hbm方式的实体映射
		Iterator<String> dirHbms=DataConfConst.ENTITY_DIR_HBM_MAP.keySet().iterator();
		String dirHbm=null;
		while(dirHbms.hasNext())
		{
			dirHbm=dirHbms.next();
			config.addFile(DataConfConst.ENTITY_DIR_HBM_MAP.get(dirHbm));
		}
		//构建sessionFactory
		DataConfConst.SESSION_FACTORY_MAP.put(dataSourceName, config.buildSessionFactory());
	}
}
