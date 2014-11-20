package com.contentbowl.commons.configuration;

import java.util.HashMap;
import java.util.Map;

import com.contentbowl.commons.guice.CommonsModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Factory for configuration services. This factory is the only one that doesn't use dependency injection
 * because you may want to use configuration services to manage dependency injection itself. That said,
 * this factory works like this: if you inform a configuration category, it tries to find a property file
 * with the same name as the category. If found, that's the implementation to be used. If not, it's going
 * to use the default implementation. 
 * 
 * @author Daniel Viveiros
 */
public class ConfigurationServiceFactory {
	
	private static final String CONF_CATEGORY = "configuration";
	private static final String DEFAULT_CATEGORY = "default";
	
	/** Guice Injector */
	private static Injector injector; 
	
	/** Default implementation of ConfigurationService */
	private static ConfigurationService configurationProperties = null;
	
	/** Map of concrete implementations */
	private static Map<String,ConfigurationService> _confServMap;
	
	static {
		_confServMap = new HashMap<String,ConfigurationService>();
		
		//injector
		injector = Guice.createInjector(new CommonsModule());
		
		//instantiates the configuration properties
		configurationProperties = injector.getInstance(BundleConfigurationServiceImpl.class);
		configurationProperties.setCategory(ConfigurationServiceFactory.CONF_CATEGORY);
		
		//puts the configuration properties in the cache
		_confServMap.put( ConfigurationServiceFactory.CONF_CATEGORY, configurationProperties );
	}
	
	/**
	 * Creates the default configuration services
	 */
	public static ConfigurationService getConfigurationService() {
		return ConfigurationServiceFactory.getConfigurationService(ConfigurationServiceFactory.DEFAULT_CATEGORY);
	}
	
	/**
	 * Creates the configuration services for a specific configuration category
	 */
	public static ConfigurationService getConfigurationService( String category ) {
		
		if (category == null) {
			category = ConfigurationServiceFactory.DEFAULT_CATEGORY;
		}
		
		//checks if the configuration service is in the cache
		ConfigurationService confServ = (ConfigurationService) _confServMap.get(category);
		if (confServ == null) {
			confServ = createConfigurationService( category );
			_confServMap.put(category, confServ);
		}
		
		return confServ;
	}
	
	/**
	 * Creates the instance of a ConfigurationService
	 */
	private static ConfigurationService createConfigurationService( String category ) {
		
		ConfigurationService confServ = null;
		
		if (category != null) {
			
			String strImplementation = null;
			try {
				strImplementation = configurationProperties.get(category);
			} catch ( ConfigurationRequiredException exc ) {
				//category not found, let's use the default
				strImplementation = configurationProperties.get(ConfigurationServiceFactory.DEFAULT_CATEGORY);
			}
			
			try {
				confServ = (ConfigurationService) injector.getInstance(Class.forName(strImplementation));
				confServ.setCategory(category); 
			} catch (Exception e) {
				throw new InvalidConfigurationServiceException(category, e);
			}
			
		}
		
		return confServ;
	}

}
