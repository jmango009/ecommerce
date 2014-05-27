/**
 * 
 */
package com.ecommerce.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author richard
 *
 */
public class StringUtil {
	
	public static boolean isEmpty(List list) {
		if (list == null || list.isEmpty()) return true;
		return false;
	}
	
	public static boolean isEmpty(String value) {
		return StringUtils.isEmpty(value);
	}

}
