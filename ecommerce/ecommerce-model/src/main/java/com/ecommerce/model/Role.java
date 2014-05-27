/**
 * 
 */
package com.ecommerce.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author richard
 *
 */
@Entity
@Table(name="ec_role")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8929723983839452691L;

    private String name; 
    private String descn; 
    private Set<Resource> resources = new HashSet<Resource>();  
    private Set<User> users = new HashSet<User>();  
      
    @SuppressWarnings("serial")
	public GrantedAuthority generateGrantedAuthority() {  
        return new GrantedAuthority() {  
            public String getAuthority() {  
                return getName();  
            }  
        };  
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	} 
    
}
