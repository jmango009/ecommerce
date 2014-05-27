/**
 * 
 */
package com.ecommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.util.BooleanUtil;

/**
 * @author richard
 *
 */
@Entity
@Table(name="ec_users")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseEntity implements UserDetails, Serializable {

	private static final long serialVersionUID = 8012937118259237285L;
	
	private String username;
	private String password;
    private Integer enabled;
    private String descn;
    
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
	
	public User() {}
	
	public User(String userName, String password) {
		this(userName, password, null);
	}
	
	public User(String username, String password, Integer enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	
	public User(String username, String password, Integer enabled, String descn) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.descn = descn;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();  
        list.add(getRole().generateGrantedAuthority());  
        return list;
	}
	
	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public boolean isEnabled() {
		return BooleanUtil.parse2Boolean(this.enabled);  
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = BooleanUtil.parse2Integer(enabled);
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
