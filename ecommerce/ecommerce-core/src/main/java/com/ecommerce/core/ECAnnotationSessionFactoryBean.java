/**
 * 
 */
package com.ecommerce.core;

import java.io.IOException;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.util.ClassUtils;

/**
 * 
 * AnnotationSessionFactoryBean can not recognised entity classes from jar.<br>
 * For this issue, We override its behavior.
 * 
 * @author richard
 *
 */
public class ECAnnotationSessionFactoryBean extends LocalSessionFactoryBean 
		implements ResourceLoaderAware {

	private static final String RESOURCE_PATTERN = "/**/*.class";

	private static final String PACKAGE_INFO_SUFFIX = ".package-info";

	private Class[] annotatedClasses;

	private String[] annotatedPackages;

	private String[] packagesToScan;
	
	private String fallbackClasspathPrefix;

	private TypeFilter[] entityTypeFilters = new TypeFilter[] {
			new AnnotationTypeFilter(Entity.class, false),
			new AnnotationTypeFilter(Embeddable.class, false),
			new AnnotationTypeFilter(MappedSuperclass.class, false),
			new AnnotationTypeFilter(org.hibernate.annotations.Entity.class, false)};

	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();


	public ECAnnotationSessionFactoryBean() {
		setConfigurationClass(AnnotationConfiguration.class);
	}


	@Override
	public void setConfigurationClass(Class<?> configurationClass) {
		if (configurationClass == null || !AnnotationConfiguration.class.isAssignableFrom(configurationClass)) {
			throw new IllegalArgumentException(
					"AnnotationSessionFactoryBean only supports AnnotationConfiguration or subclasses");
		}
		super.setConfigurationClass(configurationClass);
	}

	/**
	 * Specify annotated classes, for which mappings will be read from
	 * class-level JDK 1.5+ annotation metadata.
	 * @see org.hibernate.cfg.AnnotationConfiguration#addAnnotatedClass(Class)
	 */
	public void setAnnotatedClasses(Class[] annotatedClasses) {
		this.annotatedClasses = annotatedClasses;
	}

	/**
	 * Specify the names of annotated packages, for which package-level
	 * JDK 1.5+ annotation metadata will be read.
	 * @see org.hibernate.cfg.AnnotationConfiguration#addPackage(String)
	 */
	public void setAnnotatedPackages(String[] annotatedPackages) {
		this.annotatedPackages = annotatedPackages;
	}

	/**
	 * Specify packages to search using Spring-based scanning for entity classes in
	 * the classpath. This is an alternative to listing annotated classes explicitly.
	 * <p>Default is none. Specify packages to search for autodetection of your entity
	 * classes in the classpath. This is analogous to Spring's component-scan feature
	 * ({@link org.springframework.context.annotation.ClassPathBeanDefinitionScanner}).
	 */
	public void setPackagesToScan(String[] packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

	public void setFallbackClasspathPrefix(String fallbackClasspathPrefix) {
		this.fallbackClasspathPrefix = fallbackClasspathPrefix;
	}

	/**
	 * Specify custom type filters for Spring-based scanning for entity classes.
	 * <p>Default is to search all specified packages for classes annotated with
	 * {@code @javax.persistence.Entity}, {@code @javax.persistence.Embeddable}
	 * or {@code @javax.persistence.MappedSuperclass}, as well as for
	 * Hibernate's special {@code @org.hibernate.annotations.Entity}.
	 * @see #setPackagesToScan
	 */
	public void setEntityTypeFilters(TypeFilter[] entityTypeFilters) {
		this.entityTypeFilters = entityTypeFilters;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
	}


	/**
	 * Reads metadata from annotated classes and packages into the
	 * AnnotationConfiguration instance.
	 */
	@Override
	protected void postProcessMappings(Configuration config) throws HibernateException {
		AnnotationConfiguration annConfig = (AnnotationConfiguration) config;
		if (this.annotatedClasses != null) {
			for (Class annotatedClass : this.annotatedClasses) {
				annConfig.addAnnotatedClass(annotatedClass);
			}
		}
		if (this.annotatedPackages != null) {
			for (String annotatedPackage : this.annotatedPackages) {
				annConfig.addPackage(annotatedPackage);
			}
		}
		scanPackages(annConfig);
	}

	/**
	 * Perform Spring-based scanning for entity classes.
	 * @see #setPackagesToScan
	 */
	protected void scanPackages(AnnotationConfiguration config) {
		if (this.packagesToScan != null) {
			try {
				for (String pkg : this.packagesToScan) {
					String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
							ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
					Resource[] resources = this.resourcePatternResolver.getResources(pattern);
					
					// this section is customized logic -- start
					if ((resources == null) || (resources.length == 0)) {
						pattern = fallbackClasspathPrefix
							+ ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
						resources = this.resourcePatternResolver.getResources(pattern);
					}
					// this section is customized logic -- end
					
					MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
					for (Resource resource : resources) {
						if (resource.isReadable()) {
							MetadataReader reader = readerFactory.getMetadataReader(resource);
							String className = reader.getClassMetadata().getClassName();
							if (matchesEntityTypeFilter(reader, readerFactory)) {
								config.addAnnotatedClass(this.resourcePatternResolver.getClassLoader().loadClass(className));
							}
							else if (className.endsWith(PACKAGE_INFO_SUFFIX)) {
								config.addPackage(className.substring(0, className.length() - PACKAGE_INFO_SUFFIX.length()));
							}
						}
					}
				}
			}
			catch (IOException ex) {
				throw new MappingException("Failed to scan classpath for unlisted classes", ex);
			}
			catch (ClassNotFoundException ex) {
				throw new MappingException("Failed to load annotated classes from classpath", ex);
			}
		}
	}

	/**
	 * Check whether any of the configured entity type filters matches
	 * the current class descriptor contained in the metadata reader.
	 */
	private boolean matchesEntityTypeFilter(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException {
		if (this.entityTypeFilters != null) {
			for (TypeFilter filter : this.entityTypeFilters) {
				if (filter.match(reader, readerFactory)) {
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * This default implementation delegates to {@link #postProcessAnnotationConfiguration}.
	 */
	@Override
	protected void postProcessConfiguration(Configuration config) throws HibernateException {
		postProcessAnnotationConfiguration((AnnotationConfiguration) config);
	}

	/**
	 * To be implemented by subclasses which want to to perform custom
	 * post-processing of the AnnotationConfiguration object after this
	 * FactoryBean performed its default initialization.
	 * <p>Note: As of Hibernate 3.6, AnnotationConfiguration's features
	 * have been rolled into Configuration itself. Simply overriding
	 * {@link #postProcessConfiguration(org.hibernate.cfg.Configuration)}
	 * becomes an option as well then.
	 * @param config the current AnnotationConfiguration object
	 * @throws HibernateException in case of Hibernate initialization errors
	 */
	protected void postProcessAnnotationConfiguration(AnnotationConfiguration config) throws HibernateException {
	}

}
