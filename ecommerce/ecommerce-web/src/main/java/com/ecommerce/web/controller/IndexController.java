/**
 * 
 */
package com.ecommerce.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.core.exception.ECRuntimeException;
import com.ecommerce.model.News;
import com.ecommerce.web.consts.EcommerceConsts;
import com.ecommerce.web.service.NewsService;

/**
 * @author richard
 * 
 */
@Controller
public class IndexController {
	
	protected final Logger logger = Logger.getLogger(IndexController.class);
	
	@Resource
	private NewsService newsService;

	public NewsService getNewsService() {
		return newsService;
	}

	@RequestMapping({ "/index.ec" })
	public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response, ModelMap modelMap) throws Exception {
		List<News> allNews = getNewsService().findAllNews();
		modelMap.put(EcommerceConsts.INDEX_ALL_NEWS, allNews);
		
		logger.info("start accessing index");
		return new ModelAndView("index", modelMap);  
    }
	
	// test error page
	@RequestMapping({ "/error.ec" })
	public ModelAndView error(HttpServletRequest request,
            HttpServletResponse response, ModelMap modelMap) throws Exception {
		throw new ECRuntimeException("A internal Error happened");
    }

}
