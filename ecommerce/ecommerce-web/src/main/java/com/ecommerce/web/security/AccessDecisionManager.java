/**
 * 
 */
package com.ecommerce.web.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author richard
 * 
 */
@Deprecated
public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if (configAttributes == null) {
			return;
		}

		Iterator<ConfigAttribute> ite = configAttributes.iterator();

		while (ite.hasNext()) {

			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			System.out.println("needRole is " + needRole);
			for (GrantedAuthority ga : authentication.getAuthorities()) {

				if (needRole.trim().equals(ga.getAuthority().trim())) {

					return;
				}

			}

		}
		throw new AccessDeniedException("没有权限访问！");

	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}
