/**
 * 
 */
package com.ecommerce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author richard
 *
 */
@Entity
@Table(name="ec_resource")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Resource extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1595590350948468349L;

    private String name;  
    
    @Column(name="res_type")
    private String resType;
    
    @Column(name="res_url")
    private String resUrl;
    
    private String descn;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getResType() {
		return resType;
	}
	
	public void setResType(String resType) {
		this.resType = resType;
	}
	
	public String getResUrl() {
		return resUrl;
	}
	
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	
	public String getDescn() {
		return descn;
	}
	
	public void setDescn(String descn) {
		this.descn = descn;
	}  
    
}
