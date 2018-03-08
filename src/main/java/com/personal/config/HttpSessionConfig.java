package com.personal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/2/7 17:20
 * @Path: com.personal.config
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600, redisNamespace = "learn")
public class HttpSessionConfig {

    /**
     * 使用cookie
     * @return
     */
    @Bean
    public CookieHttpSessionStrategy cookieHttpSessionStrategy(){
        CookieHttpSessionStrategy strategy=new CookieHttpSessionStrategy();
        DefaultCookieSerializer cookieSerializer=new DefaultCookieSerializer();
        //cookies名称
        cookieSerializer.setCookieName("MYSESSIONID");
        //过期时间(秒)
        cookieSerializer.setCookieMaxAge(1800);
        strategy.setCookieSerializer(cookieSerializer);
        return strategy;
    }

    /**
     * 采用Http头取代cookie
     * @return
     */
    /*@Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }*/
}
