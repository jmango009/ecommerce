/**
 * 
 */
package com.ecommerce.web.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @author richard
 *
 */
@Deprecated
public class FilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	
	@Resource
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation invocation = new FilterInvocation(request, response, chain);  
        invoke(invocation); 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}
	
	private void invoke(FilterInvocation invocation) throws IOException,  
    		ServletException {  
		// object为FilterInvocation对象  
		// super.beforeInvocation(fi);//源码  
		// 1.获取请求资源的权限  
		//执行 Collection<ConfigAttribute> attributes =   
        //securityMetadataSource.getAttributes(fi);  
		// 2.是否拥有权限  
		// this.accessDecisionManager.decide(authenticated, fi, attributes);  
		// this.accessDecisionManager.decide(authenticated, fi, attributes);  
		InterceptorStatusToken token = super.beforeInvocation(invocation);  
		try {  
			invocation.getChain().doFilter(invocation.getRequest(), invocation.getResponse());  
		} finally {  
			super.afterInvocation(token, null);  
		}  
	}  

}
