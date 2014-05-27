/**
 * 
 */
package com.ecommerce.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ecommerce.core.service.impl.BaseServiceImpl;
import com.ecommerce.model.News;
import com.ecommerce.web.dao.NewsDao;
import com.ecommerce.web.service.NewsService;

/**
 * @author richard
 *
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl implements NewsService {
	
	@Resource
	private NewsDao newsDao;
	
	public NewsDao getNewsDao() {
		return newsDao;
	}

	@Override
	public List<News> findAllNews() {
		return getNewsDao().findAllNews();
	}

}
