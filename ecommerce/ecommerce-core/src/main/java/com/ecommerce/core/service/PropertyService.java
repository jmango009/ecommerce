/**
 * 
 */
package com.ecommerce.core.service;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.ecommerce.core.log.ECLogger;

/**
 * @author richard
 * 
 */
@Component
public final class PropertyService {

	private static ECLogger logger = ECLogger.getLogger(PropertyService.class);
	private static Properties props = null;
	
	public String getString(String resourceFileName, String propertyName) throws IOException {
		if (props == null) {
			props = getProperties(resourceFileName);
		}
		return props.getProperty(propertyName);
	}

	@PostConstruct
	private Properties getProperties(String resourceFileName) throws IOException {
		Resource resource = new ClassPathResource(resourceFileName);
		logger.info("load resources from file: " + resourceFileName);
		props = PropertiesLoaderUtils.loadProperties(resource);
		return props;
	}

}
