package com.contentbowl.commons.test;

import org.junit.After;
import org.junit.Before;

import com.contentbowl.commons.guice.CommonModule;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Abstract class for all test cases. It initializes Guice and some Google App Engine
 * services to help local test executions.
 * 
 * @author Daniel Viveiros
 */
public abstract class AbstractTestCase {
	
	private Injector injector; 
	
	/**
	 * Initializes the helper
	 */
	final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig(),
			new LocalMemcacheServiceTestConfig());
	
	public AbstractTestCase() {
		this.injector = Guice.createInjector(new CommonModule());
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
		

}
