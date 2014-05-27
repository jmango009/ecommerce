/**
 * 
 */
package com.ecommerce.core.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.core.SystemSetting;
import com.ecommerce.core.service.BaseService;
import com.ecommerce.util.PropertiesUtil;
import com.ecommerce.util.support.ECLogger;

/**
 * @author richard
 * 
 */
public class BaseServiceImpl implements BaseService {

	private static ECLogger logger = ECLogger.getLogger(BaseServiceImpl.class);

	protected void accessUserLog(HttpServletRequest request, String userName) throws IOException {
		if (PropertiesUtil.getBoolean(SystemSetting.VISIT_LOG_INDEX_ENABLE)) {
//			EventHome.publishEvent(new VisitLogEvent(request.getRemoteAddr(),
//					shopName, userName));
		} else {
			logger.info("[{}],{} visit index.",
					new Object[] { request.getRemoteAddr(), userName });
		}

	}

}
