/**
 * 
 */
package com.ecommerce.util.support;

import org.apache.log4j.Logger;

/**
 * @author richard
 *
 */
public class ECLogger {
	
	private static ECLogger instance;
	private Logger proxy;
	
	@SuppressWarnings("rawtypes")
	public static ECLogger getLogger(Class clazz) {
		if (instance == null) {
			instance = new ECLogger();
		}
		instance.proxy = Logger.getLogger(clazz);
		return instance;
	}
	
	public void info(String format, Object... args) {
		proxy.info(String.format(format, args));
	}
	
	public void debug(String format, Object... args) {
		proxy.debug(String.format(format, args));
	}
	
	public void error(String format, Object... args) {
		proxy.error(String.format(format, args));
	}
	
	public void warn(String format, Object... args) {
		proxy.warn(String.format(format, args));
	}
	
	public void info(String message) {
		proxy.info(message);
	}
	
	public void debug(String message) {
		proxy.debug(message);
	}
	
	public void error(String message) {
		proxy.info(message);
	}
	
	public void warn(String message) {
		proxy.info(message);
	}
	
}
