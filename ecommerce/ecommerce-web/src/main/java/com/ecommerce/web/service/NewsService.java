/**
 * 
 */
package com.ecommerce.web.service;

import java.util.List;

import com.ecommerce.core.service.BaseService;
import com.ecommerce.model.News;

/**
 * @author richard
 * 
 */
public interface NewsService extends BaseService {
	
	public List<News> findAllNews();

}
