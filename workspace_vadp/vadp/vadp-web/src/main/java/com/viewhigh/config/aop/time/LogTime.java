package com.viewhigh.config.aop.time;

import java.lang.annotation.*;

/**
 * @description
 *  记录方法执行时间
 *      注意：aop只支持方法，不支持类
 * @author Johnny
 * @since v1.0
 * @date 2017/12/29 下午3:14
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogTime {
    String value() default "";
}
