/**
 * 
 */
package com.ecommerce.web.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.News;
import com.ecommerce.web.dao.NewsDao;

/**
 * @author richard
 * 
 */
@Repository
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao {

	private static Logger logger = Logger.getLogger(NewsDaoImpl.class);

	@Override
	public News createNews(News news) {
		return (News) create(news);
	}

	@Override
	public void updateNews(News news) {
		update(news);
	}

	@Override
	public void deleteNews(News news) {
		logger.info("start deleting news with title: " + news.getTitle());
		delete(news);
	}

	@Override
	public List<News> findAllNews() {
		return findAll(entityClass, "dateUpdated", false);
	}

	@Override
	public List<News> findAllNewsByCategory(Integer newCategoryId) {
		Criteria criteria = getSession().createCriteria(News.class);
		criteria.add(Restrictions.eq("newsCatogoryId", newCategoryId));
		criteria.addOrder(Order.desc("dateUpdated"));
		return findByCriteria(criteria);
	}
	
	
}
