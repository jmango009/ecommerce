package com.ecommerce.web.dao;


import java.util.List;

import com.ecommerce.core.dao.BaseDao;
import com.ecommerce.model.User;

/**
 * @author richard
 *
 */
public interface UserDao extends BaseDao<User> {
	
	public User findUserByUsernameAndPassword(String username, String password); 
	
	public User findUserByUsername(String username);
	
	public List<String> testIfNamedQueryWorks(String username);
	
}
