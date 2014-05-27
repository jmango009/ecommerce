/**
 * 
 */
package com.ecommerce.util.support;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.PostLoad;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

/**
 * @author richard
 * 
 */
@Component
public final class PropertyManager {

	private static ECLogger logger = ECLogger.getLogger(PropertyManager.class);
	private static Properties props = null;
	
	public static String getString(String resourceFileName, String propertyName) throws IOException {
		if (props == null) {
			props = getProperties(resourceFileName);
		}
		return props.getProperty(propertyName);
	}

	@PostLoad
	private static Properties getProperties(String resourceFileName) throws IOException {
		Resource resource = new ClassPathResource(resourceFileName);
		logger.info("load resources from file: " + resourceFileName);
		props = PropertiesLoaderUtils.loadProperties(resource);
		return props;
	}

}
