package com.viewhigh.vadp.framework.data.conf;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * hbm文件扫描
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HbmScan {
	/**
	 * 不在classpath下面，例如WEB-INF/conf
	 * @return
	 */
	String[] basedirs() default {};
	/**
	 * classpath下面,例如:com.viewhigh
	 * @return
	 */
	String[] classpaths() default {};
}
