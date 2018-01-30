package com.viewhigh.excel.utils;

import com.viewhigh.excel.domain.Base;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.viewhigh.excel.common.ApplicationConstants.*;

public class ClazzUtil {

	/**
	 * 从包package中获取所有的Class
	 *
	 * @param packageName packageName
	 * @return Set
	 */
	public static Set<Class<?>> getClasses(String packageName, Class parentClass) {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(
					packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					System.err.println("file类型的扫描");
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath,
							recursive, classes, parentClass);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}


	/**
	 * 以文件的形式来获取包下的所有Class
	 *
	 * @param packageName packageName
	 * @param packagePath packagePath
	 * @param recursive   recursive
	 * @param classes     classes
	 */
	private static void findAndAddClassesInPackageByFile(String packageName,
														 String packagePath, final boolean recursive, Set<Class<?>> classes, Class parentClass) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "."
								+ file.getName(), file.getAbsolutePath(), recursive,
						classes, parentClass);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					// 添加到集合中去
					//classes.add(Class.forName(packageName + '.' + className));
					//经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
					Class clazz = Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className);
					if (parentClass.isAssignableFrom(clazz)) {
						classes.add(clazz);
					}
				} catch (ClassNotFoundException e) {
					// log.error("添加用户自定义视图类错误 找不到此类的.class文件");
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * 获取字段导入类型
	 *
	 * @param propType propType
	 * @return String
	 */
	public static String getDbType(String propType) {
		switch (propType) {
			case "class java.math.BigDecimal":
				return DECIMAL;
			case "class java.lang.Integer":
			case "class java.lang.Short":
				return INTEGER;
			case "class java.lang.Double":
				return DOUBLE;
			case "class java.lang.Long":
				return LONG;
			case "class java.lang.Boolean":
			case "boolean":
				return BOOLEAN;
			case "class java.util.Date":
				return TIMESTAMP;
			default:
				return VARCHAR;

		}
	}

	/**
	 * 驼峰法转下划线
	 *
	 * @param line 源字符串
	 * @return 转换后的字符串
	 */
	public static String camel2Underline(String line) {
		if (line == null || "".equals(line)) {
			return "";
		}
		String regular = "[A-Z]([a-z\\d]+)?";
		line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(word.toUpperCase());
			sb.append(matcher.end() == line.length() ? "" : "_");
		}
		return sb.toString();
	}
}
