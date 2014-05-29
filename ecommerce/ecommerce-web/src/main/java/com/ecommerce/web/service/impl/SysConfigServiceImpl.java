/**
 * 
 */
package com.ecommerce.web.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.core.service.impl.BaseServiceImpl;
import com.ecommerce.model.SysConfig;
import com.ecommerce.web.service.SysConfigService;

/**
 * @author richard
 *
 */
@Service
public class SysConfigServiceImpl extends BaseServiceImpl implements
		SysConfigService {

	@Override
	public SysConfig getSysConfig() {
		// mock SystemConfig
		SysConfig sysConfig = new SysConfig();
		sysConfig.setTitle("Multi-user ecommerce system.");
		sysConfig.setUseSecurityCode(true);
		sysConfig.setAllowSinaLogin(true);
		sysConfig.setAllowQQLogin(true);
		
		return sysConfig;
	}

}
