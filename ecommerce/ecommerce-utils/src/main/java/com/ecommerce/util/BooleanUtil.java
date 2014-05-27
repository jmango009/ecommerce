/**
 * 
 */
package com.ecommerce.util;

/**
 * @author richard
 *
 */
public class BooleanUtil {
	
	/**
	 * Only 1 can be parsed to True.
	 * 
	 * @param value
	 * @return
	 */
	public static Boolean parse2Boolean(Integer value) {
		if(value == null) {
			return Boolean.FALSE;
		} else if (value.intValue() == 1) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	/**
	 * Only True can be parsed to 1. else is 0.
	 * 
	 * @param value
	 * @return
	 */
	public static Integer parse2Integer(Boolean value) {
		if(value == null) {
			return Integer.valueOf("0");
		} else if (value) {
			return Integer.valueOf("1");
		}
		
		return Integer.valueOf("0");
	}

}
