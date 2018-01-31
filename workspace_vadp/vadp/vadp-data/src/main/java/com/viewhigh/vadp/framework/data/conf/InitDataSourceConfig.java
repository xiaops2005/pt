package com.viewhigh.vadp.framework.data.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viewhigh.vadp.framework.data.cons.DataConfConst;
import com.viewhigh.vadp.framework.data.exception.DataException;

/**
 * 初始化配置信息
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class InitDataSourceConfig {
	private static final Logger logger = LoggerFactory.getLogger(ClasspathPackageScanner.class);

	public InitDataSourceConfig(Class<?> importResourceClass) {
		initAnnotation(importResourceClass);
		initHBM(importResourceClass);
		initConf(importResourceClass);
	}

	private void initAnnotation(Class<?> importResourceClass) {
		//扫描系统中所有的注解实体类
		String errorEntity = null;
		try {
			if (importResourceClass.isAnnotationPresent(EntityScan.class)) {
				EntityScan ir = (EntityScan) importResourceClass.getAnnotation(EntityScan.class);
				String[] packages = ir.basePackages();
				//判断是否添加了EntityScan注解
				List<String> nameList = new ArrayList<String>();
				if (packages != null) {
					//分别扫描不同的子包
					for (String basePackage : packages) {
						ClasspathPackageScanner.doScan(basePackage, nameList);
					}
					//将扫描结果保存到map中备用
					for (String name : nameList) {
						errorEntity = name;
						logger.info("加载实体定义--"+name);
						DataConfConst.ENTITY_ANNOTATION_MAP.put(name, Class.forName(name));
					}
				}
			}
		} catch (Exception e) {
			logger.error("无法构造" + errorEntity, e);
			throw new DataException("无法构造" + errorEntity, e);
		}
	}

	private void initHBM(Class<?> importResourceClass) {
		//扫描系统中所有的hbm文件
		String errorEntity = null;
		try {
			if (importResourceClass.isAnnotationPresent(HbmScan.class)) {
				HbmScan ir = (HbmScan) importResourceClass.getAnnotation(HbmScan.class);
				String[] basedirs = ir.basedirs();
				String[] classpaths = ir.classpaths();
				if((basedirs==null|| basedirs.length==0) && (classpaths==null|| classpaths.length==0))
				{
					logger.error("HbmScan注解必须配置basedirs或classpaths属性");
					throw new DataException("HbmScan注解必须配置basedirs或classpaths属性",null);
				}
				//开始解析目录配置的hbm文件
				List<String> hbmList = new ArrayList<String>();
				if (basedirs != null) {
					//分别扫描不同的子包
					for (String dir : basedirs) {
						String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
						//如果是web目录那么就需要取得目录的根节点，同时配置的目录必须以/WEB-INF/开始
						if (path.indexOf("/WEB-INF/") > 0) {
							if (dir.startsWith("/")) {
								path = path.substring(0, path.indexOf("/WEB-INF/"));
							} else {
								path = path.substring(0, path.indexOf("WEB-INF/"));
							}
						}
						HbmFileScanner.doScanBaseDir(new File(path + dir), hbmList);
					}
					//将扫描结果保存到map中备用
					for (String name : hbmList) {
						errorEntity = name;
						DataConfConst.ENTITY_DIR_HBM_MAP.put(name, name);
					}
				}
				//开始解析classpath中的hbm文件
				List<String> hbmClasspathList = new ArrayList<String>();
				if (classpaths != null) {
					//分别扫描不同的子包
					for (String classpath : classpaths) {
						HbmFileScanner.doScanClassPath(classpath, hbmClasspathList);
					}
					//将扫描结果保存到map中备用
					for (String name : hbmClasspathList) {
						errorEntity = name;
						DataConfConst.ENTITY_CLASSPATH_HBM_MAP.put(name+".hbm.xml", name+".hbm.xml");
					}
				}
			}
		} catch (Exception e) {
			logger.error("无法构造" + errorEntity, e);
			throw new DataException("无法构造" + errorEntity, e);
		}
	}

	private void initConf(Class<?> importResourceClass) {
		//检查类是否有@ImportResource注解
		if (importResourceClass.isAnnotationPresent(ImportResource.class)) {
			ImportResource ir = (ImportResource) importResourceClass.getAnnotation(ImportResource.class);
			String mode = ir.mode();
			if (StringUtils.isEmpty(mode)) {
				mode = "web";
			}
			if ("web".equals(mode)) {
				LoadConfigFile lcf = new LoadConfigFile();
				lcf.loadByWeb();
			} else {
				LoadConfigFile lcf = new LoadConfigFile();
				lcf.loadByClassPath();

			}
		}
	}
}
