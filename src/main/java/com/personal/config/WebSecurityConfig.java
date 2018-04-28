package com.personal.config;

import com.personal.config.filter.JWTAuthenticationFilter;
import com.personal.config.filter.JWTLoginFilter;
import com.personal.config.filter.MyFilterSecurityInterceptor;
import com.personal.config.handler.LoginSuccessHandler;
import com.personal.config.provider.MyAuthenticationProvider;
import com.personal.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/2/6 17:13
 * @Path: com.personal.config
 * SpringSecurity配置类
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public CustomUserService customUserService() {
        return new CustomUserService();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public MyFilterSecurityInterceptor mySecurityFilter() {
        return new MyFilterSecurityInterceptor();
    }

    @Bean
    public MyAuthenticationProvider myAuthenticationProvider() {
        return new MyAuthenticationProvider();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 设置数据源 默认使用的Apache的连接池
        jdbcTokenRepository.setDataSource(dataSource);
        //设置初始化存储Token的表  方便调试 由于源码没有对数据库中是否有表结构做出判断，正常使用的时候不建议开启，不然第二次启动会报错！
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider());
        //auth.userDetailsService(customUserService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // ajax 跨域预检命令不能返回401，否则浏览器就报错了。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(mySecurityFilter(), FilterSecurityInterceptor.class)
                .headers()
                //disable X-Frame-Options
                .frameOptions().sameOrigin().disable()
                //关闭crsf
                //.csrf().disable()
                .authorizeRequests()
                //均可访问
                //.antMatchers(HttpMethod.POST, "/users/signUp")
                .antMatchers("/signUp","/statics/**").permitAll()
                //其余需要权限
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .formLogin()
                // 配置登录页面的表单 action 必须是 '/login'
                .loginPage("/login")
                .loginProcessingUrl("/login")
                //登录成功处理
                .successHandler(loginSuccessHandler())
                //用户名和密码的参数名必须是 'username' 和 'password'，
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录失败的 url 是 '/login-error'
                .failureUrl("/login?error")
                .permitAll()
                .and().logout()
                //清除session
                .invalidateHttpSession(true)
                //清除Cookie
                .deleteCookies("rememberMe")
                .permitAll()
                .and().rememberMe()
                .tokenRepository(persistentTokenRepository())
                .rememberMeServices(new PersistentTokenBasedRememberMeServices("rememberMe", customUserService(), persistentTokenRepository()))
                .tokenValiditySeconds(7 * 24 * 60 * 60)
                //权限不足处理
                .and().exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandlerImpl())
                .accessDeniedPage("/deny")
                //session管理,session失效后跳转
                .and().sessionManagement()
                .invalidSessionUrl("/login")
                //只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
                //需重写user类equals和hashcode方法用户比较
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry())
                .expiredUrl("/login");
    }
}