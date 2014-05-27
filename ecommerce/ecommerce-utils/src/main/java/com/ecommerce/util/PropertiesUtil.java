/**
 * 
 */
package com.ecommerce.util;

import java.io.IOException;

import com.ecommerce.util.support.PropertyManager;


/**
 * @author richard
 * 
 */
public class PropertiesUtil {
	
	private static final String COMMON_CONFIG_PROPERTY_FILE = "common.properties";
	
	public static String getString(String propertyName) throws IOException {
		return PropertyManager.getString(COMMON_CONFIG_PROPERTY_FILE, propertyName);
	}
	
	public static Boolean getBoolean(String propertyName) throws IOException {
		return Boolean.valueOf(getString(propertyName));
	}

}
