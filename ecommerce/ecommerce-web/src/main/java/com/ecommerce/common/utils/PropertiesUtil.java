/**
 * 
 */
package com.ecommerce.common.utils;

import java.io.IOException;

import javax.annotation.Resource;

import com.ecommerce.core.service.PropertyService;

/**
 * @author richard
 * 
 */
public class PropertiesUtil {
	
	@Resource
	private static PropertyService propertyService;
	
	private static final String COMMON_CONFIG_PROPERTY_FILE = "common.properties";
	
	public static String getString(String propertyName) throws IOException {
		return propertyService.getString(COMMON_CONFIG_PROPERTY_FILE, propertyName);
	}
	
	public static Boolean getBoolean(String propertyName) throws IOException {
		return Boolean.valueOf(getString(propertyName));
	}

}
