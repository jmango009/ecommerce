/**
 * 
 */
package com.ecommerce.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author richard
 * 
 */
@Entity
@Table(name="newsCategory")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class NewsCategory extends BaseEntity {

	private static final long serialVersionUID = -638770546773564065L;
	
	private Integer categoryType;
	private String name;
	private Short isDeleted;
	private String whoCreated;
	private Date dateCreated;
	
	@OneToMany(mappedBy="newsCategory", fetch=FetchType.LAZY,
			   cascade={javax.persistence.CascadeType.REMOVE})
	@OrderBy("dateUpdated desc")
	private List<News> articles = new ArrayList<News>();

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getWhoCreated() {
		return whoCreated;
	}

	public void setWhoCreated(String whoCreated) {
		this.whoCreated = whoCreated;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<News> getArticles() {
		return articles;
	}

	public void setArticles(List<News> articles) {
		this.articles = articles;
	}
	
}
