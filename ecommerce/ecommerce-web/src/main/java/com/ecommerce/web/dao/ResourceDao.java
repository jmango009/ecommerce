/**
 * 
 */
package com.ecommerce.web.dao;

import java.util.List;

import org.springframework.security.access.ConfigAttribute;

import com.ecommerce.core.dao.BaseDao;
import com.ecommerce.model.Resource;

/**
 * @author richard
 *
 */
public interface ResourceDao extends BaseDao<Resource> {
	
	List<ConfigAttribute> loadRolesByResource(String resouceUrl);

}
