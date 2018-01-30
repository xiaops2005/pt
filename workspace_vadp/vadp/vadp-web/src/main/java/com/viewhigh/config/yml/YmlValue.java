package com.viewhigh.config.yml;

import cn.hutool.core.util.StrUtil;
import com.viewhigh.util.Strings;
import com.viewhigh.vadp.framework.data.cons.DataConfConst;
import com.viewhigh.vadp.framework.data.exception.DataException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.DefaultNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * @author seven
 * @description 读取yaml配置文件
 * update 2018.1.16  读取vadp-data.ßproperties配置文件
 * @date 2017/12/20 下午2:13
 * @since v1.0
 */
@Component("ymlValue")
public class YmlValue {
    private static Logger log = LoggerFactory.getLogger(YmlValue.class);
    private Properties props;
    private YmlValue(){
        //加载vadp-data.properties
        LoadConfigFile configFile = new LoadConfigFile();
        props = configFile.loadByWeb();
    }

    @Autowired
    private Environment env;

    public String getUrl() {
        return props.getProperty("datasource.default.hibernate.connection.url");
    }

    public String getUsername() {
        return props.getProperty("datasource.default.hibernate.connection.username");
    }

    public String getPassword() {
        return props.getProperty("datasource.default.hibernate.connection.password");
    }

    public String getDriverClassName() {
        return props.getProperty("datasource.default.hibernate.connection.driver_class");
    }

}

/**
 * @description
 *      读取vadp-data.properties, 源代码参考vadp-data中读取vadp-data.properties的方法
 * @author Johnny
 * @since v1.0
 * @date 2018/1/16 下午1:06
 */
class LoadConfigFile {

    private static final String CONF_FILE_DATASOURCE_NAME = "vadp-data.properties";
    private static final Logger logger = LoggerFactory.getLogger(LoadConfigFile.class);
    /**
     * web方式加载配置文件
     */
    public Properties loadByWeb() {
        //取得classpath
        String fullPath = this.getClass().getResource("").getPath();
        String webInfPath = null;
        //获得WEB-INF/conf目录路径
        File componentrefFile = null;
        int libPath = fullPath.lastIndexOf("lib");
        if (libPath > 0) {
            webInfPath = fullPath.substring(0, libPath - 1);
            if (webInfPath.startsWith("file")) {
                webInfPath = webInfPath.substring(5);
            }
            componentrefFile = new File(webInfPath + "/conf/"+CONF_FILE_DATASOURCE_NAME);
        }
        int classespath = fullPath.lastIndexOf("classes");
        if (libPath < 0 && webInfPath == null) {
            webInfPath = fullPath.substring(0, classespath - 1);
            componentrefFile = new File(webInfPath + "/conf/"+CONF_FILE_DATASOURCE_NAME);
        }
        Properties pro = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(componentrefFile);
            pro.load(in);
        } catch (Exception e) {
            logger.error("YmlValue读取资源文件错误！！！"+componentrefFile);
            throw new DataException("13001",e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                logger.error("YmlValue读取资源文件错误！！！"+componentrefFile);
                throw new DataException("13001",e);
            }
            return pro;
        }
    }
}
