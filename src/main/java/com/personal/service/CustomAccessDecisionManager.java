package com.personal.service;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 *AccessdecisionManager在Spring security中是很重要的。
 *
 *在验证部分简略提过了，所有的Authentication实现需要保存在一个GrantedAuthority对象数组中。
 *这就是赋予给主体的权限。 GrantedAuthority对象通过AuthenticationManager
 *保存到 Authentication对象里，然后从AccessDecisionManager读出来，进行授权判断。
 *
 *Spring Security提供了一些拦截器，来控制对安全对象的访问权限，例如方法调用或web请求。
 *一个是否允许执行调用的预调用决定，是由AccessDecisionManager实现的。
 *这个 AccessDecisionManager 被AbstractSecurityInterceptor调用，
 *它用来作最终访问控制的决定。 这个AccessDecisionManager接口包含三个方法：
 *
 void decide(Authentication authentication, Object secureObject,List<ConfigAttributeDefinition> config) throws AccessDeniedException;
 boolean supports(ConfigAttribute attribute);
 boolean supports(Class clazz);

 从第一个方法可以看出来，AccessDecisionManager使用方法参数传递所有信息，这好像在认证评估时进行决定。
 特别是，在真实的安全方法期望调用的时候，传递安全Object启用那些参数。
 比如，让我们假设安全对象是一个MethodInvocation。
 很容易为任何Customer参数查询MethodInvocation，
 然后在AccessDecisionManager里实现一些有序的安全逻辑，来确认主体是否允许在那个客户上操作。
 如果访问被拒绝，实现将抛出一个AccessDeniedException异常。

 这个 supports(ConfigAttribute) 方法在启动的时候被
 AbstractSecurityInterceptor调用，来决定AccessDecisionManager
 是否可以执行传递ConfigAttribute。
 supports(Class)方法被安全拦截器实现调用，
 包含安全拦截器将显示的AccessDecisionManager支持安全对象的类型。
 */
@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {

    //这里验证完了之后，抛出的异常，被哪个过滤器吃掉了？
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if( configAttributes == null ) {
            return ;
        }
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while( iterator.hasNext()){
            ConfigAttribute configAttribute = iterator.next();
            String needRole = ((SecurityConfig)configAttribute).getAttribute();
            //grantedAuthority 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for( GrantedAuthority grantedAuthority: authentication.getAuthorities()){
                if(needRole.trim().equals(grantedAuthority.getAuthority().trim())){
                    return;
                }
            }
        }
        /**
         * 如果一个AccessDeniedException被抛出并且用户已经被认证，那么这意味着一个操作已经尝试了它们不具有足够的权限。
         * 在这种情况下，ExceptionTranslationFilter将调用第二策略，AccessDeniedHandler。
         * 默认情况下,AccessDeniedHandlerImpl被使用，这只是发送一个403（禁止）响应于客户端。
         * 此外，还可以配置明确的实例，并设置一个错误页面的URL，它会请求转发 .
         * 这可以是一个简单的“拒绝访问”页上，如一个JSP，或者它可以是更复杂的处理程序，如一个MVC的控制器。
         * 当然，你可以自己实现接口，并使用自己的实现。
         */
        throw new AccessDeniedException("权限不足");
    }

    public boolean supports( ConfigAttribute attribute ){
        return true;//都要设为true

    }

    public boolean supports(Class<?> clazz){
        return true;//都要设为true
    }


}
