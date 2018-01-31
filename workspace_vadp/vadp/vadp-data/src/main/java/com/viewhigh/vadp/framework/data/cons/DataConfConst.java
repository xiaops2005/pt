package com.viewhigh.vadp.framework.data.cons;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;

/**
 * 配置常量
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class DataConfConst {
	public static final String DRIVER="hibernate.connection.driver_class";
	public static final String USER="hibernate.connection.username";
	public static final String PASS="hibernate.connection.password";
	public static final String POOL_SIZE="connection.pool_size";
	public static final String URL="hibernate.connection.url";
	public static final String SHOW_SQL="show_sql";
	public static final String FORMAT_SQL="format_sql";
	public static final String DIALECT="hibernate.dialect";
	public static final String AUTOCOMMIT="hibernate.connection.autocommit";
	public static final String CURRENT_SESSION_CONTEXT_CLASS="hibernate.current_session_context_class";
	public static final String CONNECTION_PROVODER= "hibernate.connection.provider_class";
	public static final String DATASOURCE=  "hibernate.datasource.name";
	//默认数据源前缀
	public static final String DEFAULT_DATASOURCE_PRIFIX="default";
	//自定义数据源前缀
	public static final String DATASOURCE_PRIFIX="datasource";
	//自定义数据源名称列表
	public static final String CUSTOM_DATASOURCE_NAMES="custom.datasource.names";
	//事务设置配置
	public static final String TRANSACTION_BEAN_NAMES="transaction.bean.names";
	public static final String TRANSACTION_BEAN_METHODS_ENABLE="transaction.bean.methods.enable";
	public static final String TRANSACTION_BEAN_METHODS_READONLY="transaction.bean.methods.readonly";

	
	//保存系统所有的SessionFactory
	public static final Map<String,SessionFactory> SESSION_FACTORY_MAP=new HashMap<String,SessionFactory>();
	//自定义数据源Map
	public static final Map<String,DataSource> DATASOURCE_MAP=new HashMap<String,DataSource>();
	//保存注解类型的实体类的路径信息
	public static final Map<String,Class<?>> ENTITY_ANNOTATION_MAP=new HashMap<String,Class<?>>();
	//保存classPath中的hbm配置类型的实体路径信息
	public static final Map<String,String> ENTITY_CLASSPATH_HBM_MAP=new HashMap<String,String>();
	//保存目录中的hbm配置类型的实体路径信息
	public static final Map<String,String> ENTITY_DIR_HBM_MAP=new HashMap<String,String>();
	//事务配置信息保存
	public static final Map<String,String> TRANSACTION_MAP=new HashMap<String,String>();
}
