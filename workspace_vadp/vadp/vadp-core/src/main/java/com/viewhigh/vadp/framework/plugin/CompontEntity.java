package com.viewhigh.vadp.framework.plugin;

import java.util.Properties;
/**
 * 组件对象
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class CompontEntity implements Comparable<CompontEntity>{
    
    public CompontEntity(String name, Properties prop, int order){
        this.prop = prop;
        this.order = order;
        this.name = name;
    }

    private String name;
    
    private Properties prop;
    
    private int order;
    
    private ICompontLoader plugin;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(CompontEntity o) {
        return this.order - o.getOrder();
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public ICompontLoader getPlugin() {
        return plugin;
    }

    public void setPlugin(ICompontLoader plugin) {
        this.plugin = plugin;
    }
}
