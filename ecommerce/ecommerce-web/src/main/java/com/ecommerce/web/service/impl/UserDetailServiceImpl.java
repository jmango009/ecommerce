/**
 * 
 */
package com.ecommerce.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.core.exception.ECRuntimeException;
import com.ecommerce.core.service.impl.BaseServiceImpl;
import com.ecommerce.model.User;
import com.ecommerce.web.dao.UserDao;
import com.ecommerce.web.service.UserDetailService;

/**
 * @author richard
 *
 */
@Service
public class UserDetailServiceImpl extends BaseServiceImpl implements UserDetailService {
	
	@Resource
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user =  getUserDao().findUserByUsername(username);
		if (user == null) {  
            throw new ECRuntimeException("Username '" + username + "' not found");  
        }  
		List<GrantedAuthority> auths = getUserDao().findUserAuthorityByName(username);
		user.setAuths(auths);
		
		return user;
	}

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		return getUserDao().findUserByUsernameAndPassword(username, password);
	}

}
