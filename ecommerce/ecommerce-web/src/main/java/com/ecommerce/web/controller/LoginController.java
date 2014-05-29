/**
 * 
 */
package com.ecommerce.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.model.SysConfig;
import com.ecommerce.util.CommonUtil;
import com.ecommerce.web.consts.EcommerceConsts;
import com.ecommerce.web.service.SysConfigService;

/**
 * @author richard
 * 
 */
@Controller
public class LoginController {
	
	protected final Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource
	SysConfigService sysConfigService; 
	
	public SysConfigService getSysConfigService() {
		return sysConfigService;
	}

	@RequestMapping({ "/login.ec" })
	public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response, ModelMap modelMap) throws Exception {
		SysConfig sysConfig = getSysConfigService().getSysConfig();
		modelMap.put(EcommerceConsts.SYS_CONFIG, sysConfig);
		String webPath = CommonUtil.getURL(request);
		modelMap.put(EcommerceConsts.WEBPATH, webPath);
		
		logger.info("start accessing login page.");
		return new ModelAndView("login", modelMap);  
    }
	
}
