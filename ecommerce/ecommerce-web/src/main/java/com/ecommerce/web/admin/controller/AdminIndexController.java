/**
 * 
 */
package com.ecommerce.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author richard
 *
 */
@Controller
@RequestMapping({ "/admin" })
public class AdminIndexController {
	
	protected final Logger logger = Logger.getLogger(AdminIndexController.class);
	
	@RequestMapping({ "/index" })
	public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response, ModelMap modelMap) throws Exception {
		logger.info("start accessing admin index");
		
		return new ModelAndView("admin/index", modelMap);  
    }
	
	@RequestMapping({ "/accessdenied" })
	public ModelAndView accessdenied(HttpServletRequest request,
            HttpServletResponse response, ModelMap modelMap) throws Exception {
		logger.info("start accessing admin index");
		
		return new ModelAndView("admin/accessdenied", modelMap);  
    }
	
}
