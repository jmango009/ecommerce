/**
 * 
 */
package com.ecommerce.web.dao;

import java.util.List;

import com.ecommerce.core.dao.BaseDao;
import com.ecommerce.model.News;

/**
 * @author richard
 *
 */
public interface NewsDao extends BaseDao<News> {
	
	public News createNews(News news);
	
	public void updateNews(News news);
	
	public void deleteNews(News news);
	
	public List<News> findAllNews();
	
	public List<News> findAllNewsByCategory(Integer newCategoryId);

}
