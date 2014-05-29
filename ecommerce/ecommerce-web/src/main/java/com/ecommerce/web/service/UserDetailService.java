/**
 * 
 */
package com.ecommerce.web.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecommerce.model.User;

/**
 * @author richard
 *
 */
public interface UserDetailService extends UserDetailsService {
	
	public User findUserByUsernameAndPassword(String username, String password); 
	
	public List<String> testIfNamedQueryWorks(String username);
}
