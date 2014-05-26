/**
 * 
 */
package com.ecommerce.web.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.common.SystemSetting;
import com.ecommerce.common.utils.PropertiesUtil;
import com.ecommerce.core.log.ECLogger;
import com.ecommerce.web.service.BaseService;

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
