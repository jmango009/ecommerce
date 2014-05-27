/**
 * 
 */
package com.ecommerce.model;

import java.io.Serializable;

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
@Table(name="ec_roles")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8929723983839452691L;

    private String name; 
    private String descn; 
      
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

}
