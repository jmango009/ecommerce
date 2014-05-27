/**
 * 
 */
package com.ecommerce.web.controller;

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
public class LoginController {
	
	protected final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping({ "/login" })
	public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response, ModelMap modelMap) throws Exception {
		logger.info("start accessing index");
		
		return new ModelAndView("login", modelMap);  
    }
	
}
