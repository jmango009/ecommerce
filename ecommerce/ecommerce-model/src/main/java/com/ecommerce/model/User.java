/**
 * 
 */
package com.ecommerce.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author richard
 *
 */
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = 8012937118259237285L;
	
	private String userName;
	private String realName;
	private String password;
	private String name;
	private String enabled;
	private String note;

	public User() {
		
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 
		return null;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

}
