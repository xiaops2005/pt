package com.viewhigh.vadp.framework.data.conf;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 导入持久层配置信息
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ImportResource {
	/**
	 * 该属性设置是否是web方式加载，还是classpath方式对应的值只能是web或classpath
	 * @return
	 */
	
	String mode() default "web";

}
