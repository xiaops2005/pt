package com.viewhigh.vadp.framework.data.conf;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viewhigh.vadp.framework.data.exception.DataException;

/**
 * hbm文件扫描加载器
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class HbmFileScanner {
	private static final Logger logger = LoggerFactory.getLogger(HbmFileScanner.class);

	/**
	 * 扫描baseDir中的所有的配置文件
	 * @param file
	 * @param nameList
	 */
	public static void doScanBaseDir(File file, List<String> nameList) {
		if (file != null) {
			if (file.isDirectory()) {
				File[] fileArray = file.listFiles();
				if (fileArray != null) {
					for (int i = 0; i < fileArray.length; i++) {
						//递归调用
						doScanBaseDir(fileArray[i], nameList);
					}
				}
			} else {
				if (file.getName().endsWith(".hbm.xml")) {
					nameList.add(file.getAbsolutePath());
				}
			}
		}
	}

	/**
	 * 扫描class中的所有的配置文件
	 * @param basedir
	 * @param nameList
	 */
	public static void doScanClassPath(String basePackage, List<String> nameList) {

		//是否循环迭代  
		boolean recursive = true;
		//获取包的名字 并进行替换  
		String packageDirName = basePackage.replace('.', '/');
		//定义一个枚举的集合 并进行循环来处理这个目录下的things  
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			//循环迭代下去  
			while (dirs.hasMoreElements()) {
				//获取下一个元素  
				URL url = dirs.nextElement();
				//得到协议的名称  
				String protocol = url.getProtocol();
				//如果是以文件的形式保存在服务器上  
				if ("file".equals(protocol)) {
					//获取包的物理路径  
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					//以文件的方式扫描整个包下的文件 并添加到集合中  
					findAndAddClassesInPackageByFile(basePackage, filePath, recursive, nameList);
				} else if ("jar".equals(protocol)) {
					//如果是jar包文件   
					//定义一个JarFile  
					JarFile jar;
					try {
						//获取jar  
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						//从此jar包 得到一个枚举类  
						Enumeration<JarEntry> entries = jar.entries();
						//同样的进行循环迭代  
						while (entries.hasMoreElements()) {
							//获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件  
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							//如果是以/开头的  
							if (name.charAt(0) == '/') {
								//获取后面的字符串  
								name = name.substring(1);
							}
							//如果前半部分和定义的包名相同  
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								//如果以"/"结尾 是一个包  
								if (idx != -1) {
									//获取包名 把"/"替换成"."  
									basePackage = name.substring(0, idx).replace('/', '.');
								}
								//如果可以迭代下去 并且是一个包  
								if ((idx != -1) || recursive) {
									//如果是一个.hbm文件 而且不是目录  
									if (name.endsWith(".hbm.xml") && !entry.isDirectory()) {
										//去掉后面的".hbm" 获取真正的类名  
										String className = name.substring(basePackage.length() + 1, name.length() - 8);
										//添加到classes  
										

										String quPath = basePackage.replaceAll("\\.", "/")+ '/' + className;
										nameList.add(quPath);
									}
								}
							}
						}
					} catch (IOException e) {
						logger.error("扫描实体类错误！！！", e);
						throw new DataException("扫描实体类错误！！！", e);
					}
				}
			}
		} catch (IOException e) {
			logger.error("扫描实体类错误！！！", e);
			throw new DataException("扫描实体类错误！！！", e);
		}
	}

	/** 
	* 以文件的形式来获取包下的所有Class 
	* @param packageName 
	* @param packagePath 
	* @param recursive 
	* @param classes 
	*/
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath,
			final boolean recursive, List<String> nameList) {
		//获取此包的目录 建立一个File  
		File dir = new File(packagePath);
		//如果不存在或者 也不是目录就直接返回  
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		//如果存在 就获取包下的所有文件 包括目录  
		File[] dirfiles = dir.listFiles(new FileFilter() {
			//自定义过滤规则 如果可以循环(包含子目录) 或则是以.hbm结尾的文件
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".hbm.xml"));
			}
		});
		//循环所有文件  
		for (File file : dirfiles) {
			//如果是目录 则继续扫描  
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,
						nameList);
			} else {
				if (file.getName().endsWith(".hbm.xml")) {
					//如果是java类文件 去掉后面的.hbm 只留下类名  
					String hbmName = file.getName().substring(0, file.getName().length() - 8);
					//添加到集合中去 
					String quPath = packageName.replaceAll("\\.", "/") + '/' + hbmName;
					nameList.add(quPath);
				}
			}
		}
	}
}
