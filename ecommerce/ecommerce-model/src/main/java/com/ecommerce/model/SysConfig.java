/**
 * 
 */
package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author richard
 * 
 */
//@Entity
//@Table(name="sysconfig")
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SysConfig extends BaseEntity {

	private static final long serialVersionUID = -3043135572139617990L;

	private String title;
	private boolean useSecurityCode;
	private boolean allowSinaLogin;
	private boolean allowQQLogin;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public boolean isUseSecurityCode() {
		return useSecurityCode;
	}

	public void setUseSecurityCode(boolean useSecurityCode) {
		this.useSecurityCode = useSecurityCode;
	}

	public boolean isAllowSinaLogin() {
		return allowSinaLogin;
	}
	
	public void setAllowSinaLogin(boolean allowSinaLogin) {
		this.allowSinaLogin = allowSinaLogin;
	}
	
	public boolean isAllowQQLogin() {
		return allowQQLogin;
	}
	
	public void setAllowQQLogin(boolean allowQQLogin) {
		this.allowQQLogin = allowQQLogin;
	}
	
}
