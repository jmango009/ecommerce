/**
 * 
 */
package com.ecommerce.web.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.ecommerce.model.Resource;
import com.ecommerce.web.dao.ResourceDao;

/**
 * @author richard
 * 
 */
@Component
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	@javax.annotation.Resource
	private ResourceDao resourcesDao;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public SecurityMetadataSource() {
		System.out.println("Loading SecurityMetadataSource..." + resourcesDao);
		loadResourceDefine();
	}

	/**
	 * load relationship of resources and authorities
	 */
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resource> resources = resourcesDao.findAll(Resource.class);
			for (Resource resource : resources) {
				List<ConfigAttribute> auths = 
						resourcesDao.loadRolesByResource(resource.getResUrl());
				resourceMap.put(resource.getName(), auths);
			}
		}
	}

	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// object是一个URL，被用户请求的url
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		System.out.println("requestUrl is " + requestUrl);

		int firstQuestionMarkIndex = requestUrl.indexOf("?");

		if (firstQuestionMarkIndex != -1) {
			requestUrl = requestUrl.substring(0, firstQuestionMarkIndex);
		}

		if (resourceMap == null) {
			loadResourceDefine();
		}
		Iterator<String> ite = resourceMap.keySet().iterator();

		while (ite.hasNext()) {
			String resURL = ite.next();

			if (resURL.equals(requestUrl)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}
