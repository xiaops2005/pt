package com.viewhigh.config.aop.time;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Johnny
 * @description aop切点配置,用来使查询指定方法的处理时间生效
 * @date 2017/12/29 下午3:16
 * @since v1.0
 */
@Component
@Aspect
public class LogTimeAspect {
    private static final Logger log = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    @Pointcut("@annotation(com.viewhigh.config.aop.time.LogTime)")
    public void logTime() {
    }

    @Around("logTime()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 确定是否启用

        long start = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String clzName = method.getDeclaringClass().getName();//获取被拦截的类名
        String methodName = method.getName(); //获取被拦截的方法名
        Object obj = pjp.proceed();

        long end = System.currentTimeMillis();
        double executeTime = (double)(end - start)/1000;
        log.debug("方法：{} [{}] 花费了 {} s ",clzName,methodName,executeTime);
        return obj;
    }
}

