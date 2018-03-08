package com.personal.config.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/2/24 9:57
 * @Path: com.personal.config.handler
 */
@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {

        log.info("Async method: {} has uncaught exception,params:{}", JSON.toJSONString(params));

        log.error("=========================="+ex.getMessage()+"=======================", ex);

        log.error("exception method:"+method.getName());

        ex.printStackTrace();
    }
}
