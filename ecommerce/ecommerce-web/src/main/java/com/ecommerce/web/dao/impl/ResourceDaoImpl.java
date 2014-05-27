/**
 * 
 */
package com.ecommerce.web.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import com.ecommerce.core.dao.impl.BaseDaoImpl;
import com.ecommerce.core.exception.ECRuntimeException;
import com.ecommerce.model.Resource;
import com.ecommerce.util.support.ECLogger;
import com.ecommerce.web.consts.DaoQueryConsts;
import com.ecommerce.web.dao.ResourceDao;

/**
 * @author richard
 *
 */
@SuppressWarnings("unchecked")
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {

	private static final ECLogger logger = ECLogger.getLogger(ResourceDaoImpl.class);
	
	@Override
	public List<ConfigAttribute> loadRolesByResource(String resouceUrl) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(DaoQueryConsts.RESOURCE_URL, resouceUrl);
		List<Map<String, Object>> roleList = null;
		try {
			roleList = findByNamedQuery(DaoQueryConsts.FIND_ROLES_BY_RESOURCE, params);
		} catch (Exception e) {
			logger.error("find roles by username failed." + e);
			throw new ECRuntimeException("find roles by username failed.");
		}
		
		List<ConfigAttribute> auths = new ArrayList<ConfigAttribute>();
		for(Map<String, Object> map: roleList){
			ConfigAttribute auth = new SecurityConfig(String.valueOf(map.get("role")));
			auths.add(auth);
		}
		
		return auths;
	}

}
