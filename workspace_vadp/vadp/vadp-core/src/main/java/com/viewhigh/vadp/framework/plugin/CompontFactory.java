package com.viewhigh.vadp.framework.plugin;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;

/**
 * 组件加载入口类
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
@WebListener
public class CompontFactory implements ServletContextListener {
	public final static String COMPONT_CONFIG_PATH = "/WEB-INF/conf/";
	private final static String KEY_INITLOADPROPERTIES_EXTNAME = "*.compont.properties";
	private final static String KEY_INITLOADHANDLER = "DefineClassCompont";//插件类
	private final static String KEY_STARTUPORDER = "Order";//插件加载顺序
	private final static String KEY_PLUGINNAME = "Name";//插件名称
	private final static Logger log = LoggerFactory.getLogger(CompontFactory.class);
	private final Deque<CompontEntity> loaderEntityArr = new LinkedList<CompontEntity>();

	/**
	 * 加载所有组件的启动配置
	 * 
	 * @param ctx
	 *            上下文
	 * @throws IOException
	 *             读取文件错误
	 * @throws ClassNotFoundException
	 *             类未找到
	 * @throws IllegalAccessException
	 *             非法异常
	 * @throws InstantiationException
	 *             初始化时发生错误
	 */
	private void loadAllPlugins(ServletContext context) throws IOException {
		ServletContextResourcePatternResolver scrpr = new ServletContextResourcePatternResolver(context);
		Resource[] resources = scrpr.getResources("/WEB-INF/conf/" + KEY_INITLOADPROPERTIES_EXTNAME);
		if (resources != null && resources.length > 0) {
			PriorityQueue<CompontEntity> queue = new PriorityQueue<CompontEntity>(resources.length);
			for (Resource resource : resources) {
				log.info("开始初始化插件配置文件:{}", resource.getFilename());

				Properties prop = PropertiesLoaderUtils.loadProperties(resource);
				String name = prop.getProperty(KEY_PLUGINNAME);
				if (StringUtils.isEmpty(name)) {
					throw new NullPointerException(resource.getFilename() + "配置文件中，未设置插件名称属性[" + KEY_PLUGINNAME + "]");
				}

				String beanName = prop.getProperty(KEY_INITLOADHANDLER);
				if (StringUtils.isEmpty(beanName)) {
					throw new NullPointerException("组件[" + name + "]未设置启动类的Bean ID属性[" + KEY_INITLOADHANDLER + "]");
				}

				int order;
				try {
					order = Integer.parseInt(prop.getProperty(KEY_STARTUPORDER));
				} catch (Exception e) {
					throw new NullPointerException("组件[" + name + "]未正确设置启动顺序属性[" + KEY_STARTUPORDER + "]");
				}
				queue.add(new CompontEntity(name, prop, order));
			}

			CompontEntity config = queue.poll();
			while (config != null) {
				Properties prop = config.getProp();
				String beanName = prop.getProperty(KEY_INITLOADHANDLER);
				try {
					ICompontLoader plugin = (ICompontLoader) Class.forName(beanName).newInstance();
					config.setPlugin(plugin);
					log.info("开始初始化组件[{}]", config.getName());
					plugin.init(prop);
					log.info("组件[{}]初始化完成", config.getName());
					config.setProp(null);
					loaderEntityArr.add(config);
					config = queue.poll();
				} catch (Exception e) {
					log.info("组件[{}]初始化错误！！！", config.getName(), e);
				}
			}
		}
	}

	/**
	 * 通过类路径来取工程路径
	 * 
	 * @return String
	 */
	private String getAbsolutePath() {
		String webPath = getClass().getClassLoader().getResource("/").getPath();
		if (webPath != null) {
			int n = webPath.lastIndexOf("WEB-INF");
			webPath = webPath.substring(0, n);
		}
		return webPath;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		try {
			loadAllPlugins(context);
		} catch (Exception e) {
			log.error("加载组件时发生错误{}", e.getMessage(), e);
		}

	}
}
