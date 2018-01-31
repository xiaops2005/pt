package com.viewhigh.vadp.framework.plugin;

import java.util.Properties;

import com.viewhigh.vadp.framework.exception.PluginInitializationException;

/**
 * 组件加载实现接口
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public interface ICompontLoader {
	/**
     * 组件初始化时回调方法
     * @param p 设定的参数信息，在每个*.compont.proerties中被定义
     * @return true 加载成功 false 加载失败
     */
    void init(Properties prop) throws PluginInitializationException;

    /**
     * 关闭时回电方法
     */
    void destroy();
}
