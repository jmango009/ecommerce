/**
 * 
 */
package com.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author richard
 * 
 */
@Entity
@Table(name="news")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class News extends BaseEntity {

	private static final long serialVersionUID = 5090576339291784116L;
	
	@ManyToOne
	@JoinColumn(name="newsCategoryId")
	private NewsCategory newsCategory;
	
	@Lob
	@Column(columnDefinition="LongText")
	private String content;
	
	private String title;
	private String url;
	private String whoCreated;
	private String whoUpdated;
	private Date dateCreated;
	private Date dateUpdated;

	public News(String title, String content, String url,
			String whoCreated, String whoUpdated, 
			Date dateCreated, Date dateUpdated) {
		this.title = title;
		this.content = content;
		this.url = url;
		this.whoCreated = whoCreated;
		this.whoUpdated = whoUpdated;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
	}

	public News() {
	}

	public News(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public NewsCategory getNewsCategory() {
		return newsCategory;
	}

	public void setNewsCategory(NewsCategory newsCategory) {
		this.newsCategory = newsCategory;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWhoCreated() {
		return whoCreated;
	}

	public void setWhoCreated(String whoCreated) {
		this.whoCreated = whoCreated;
	}

	public String getWhoUpdated() {
		return whoUpdated;
	}

	public void setWhoUpdated(String whoUpdated) {
		this.whoUpdated = whoUpdated;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" [title=").append(this.title)
				.append(", content=").append(", whoCreated=")
				.append(this.whoCreated).append(", dateCreated=")
				.append(this.dateCreated).append("]");
		return sb.toString();
	}
}
