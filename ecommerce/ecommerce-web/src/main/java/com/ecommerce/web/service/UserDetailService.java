/**
 * 
 */
package com.ecommerce.web.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecommerce.model.User;

/**
 * @author richard
 *
 */
public interface UserDetailService extends UserDetailsService {
	
	public User findUserByUsernameAndPassword(String username, String password); 
}
