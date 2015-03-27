package com.contentbowl.commons.test;

import org.junit.After;
import org.junit.Before;

import com.contentbowl.commons.configuration.ConfigurationService;
import com.contentbowl.commons.configuration.ConfigurationServiceFactory;
import com.contentbowl.commons.guice.InjectionUtils;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;
import com.google.inject.Injector;

/**
 * Abstract class for all test cases. It initializes Guice and some Google App Engine
 * services to help local test executions.
 * 
 * @author Daniel Viveiros
 */
public abstract class AbstractTestCase {
	
	private Injector injector;
	private ConfigurationService confService;
	
	/**
	 * Initializes the helper
	 */
	final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalTaskQueueTestConfig(),
			new LocalDatastoreServiceTestConfig(),
			new LocalMemcacheServiceTestConfig());
	
	public AbstractTestCase() {
		this.injector = InjectionUtils.createInjector();
		confService = ConfigurationServiceFactory.getConfigurationService("test");
	}
	
	/**
	 * Helper setup
	 */
	@Before
	public void helperSetup() {
		helper.setUp();
	}

	/**
	 * Shuts down the helper
	 */
	@After
	public void helperTearDown() {
		helper.tearDown();
	}
	
	/**
	 * Creates an instance of a object using Guice
	 * 
	 * @param type Type of the class to be initiated
	 * @return An instance of the class
	 */
	protected <T> T getInstance(Class<T> type) {
        return this.injector.getInstance(type);
    }
	
	/**
	 * Get a test property
	 */
	protected String getTestProperty( String key ) {
	    return confService.get(key);
	}
	
	/**
	 * Return if the tests are configured to run in the online mode or not
	 */
	protected Boolean isOnline() {
	    return "true".equals( getTestProperty( "is_online") );
	}
		

}
