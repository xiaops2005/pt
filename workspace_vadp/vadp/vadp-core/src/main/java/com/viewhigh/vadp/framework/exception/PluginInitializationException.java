package com.viewhigh.vadp.framework.exception;

import com.viewhigh.vadp.framework.exception.CoreException;

/**
 * 组件加载异常类
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class PluginInitializationException extends CoreException{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param errorCode
     * @param errorInfoParameters
     * @param cause
     */
    public PluginInitializationException(String errorCode, Throwable cause,String[] errorInfoParameters) {
        super(errorCode,cause, errorInfoParameters);
    }

}
