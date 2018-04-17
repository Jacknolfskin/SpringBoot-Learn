package com.personal.aspect;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Web层日志切面
 *
 * @author Jacknolfskin
 * @version 1.0.0
 * @date 17/11/17 上午10:42.
 * Order值越小，优先级越高
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {

    private Logger logger = Logger.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.personal.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)){
            return;
        }
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "joinPoint", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {
        // 处理完请求，返回内容
        long usedTime = System.currentTimeMillis() - startTime.get();
        Object[] arr = joinPoint.getArgs();
        List<Object> list = Lists.newArrayList();
        for (Object obj : arr) {
            if (obj == null) {
                continue;
            }
            if (obj instanceof HttpServletRequest || obj instanceof HttpServletResponse) {
                continue;
            }
            list.add(obj.toString());
        }
        String args = Joiner.on(",").join(list);
        String key = String.format("method=[%s.%s]",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName());
        if (usedTime > 100) {
            logger.info(String.format("method=[%s],args=[%s] use time=%d ms", key, args, usedTime));
        } else if (logger.isDebugEnabled()) {
            logger.debug(String.format("method=[%s],args=[%s] use time=%d ms", key, args, usedTime));
        }
    }

    @After("webLog()")
    public void after() {
        logger.info("最终通知");
    }

    @AfterThrowing("webLog()")
    public void doAfterThrow() {
        logger.info("例外通知");
    }

    @Around("webLog()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("进入环绕通知");
        //执行该方法
        Object object = pjp.proceed();
        logger.info("退出方法");
        return object;
    }
}

