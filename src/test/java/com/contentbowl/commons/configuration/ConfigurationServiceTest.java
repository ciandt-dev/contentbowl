package com.contentbowl.commons.configuration;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.contentbowl.commons.test.AbstractTestCase;

public class ConfigurationServiceTest extends AbstractTestCase {
	

	/**
	 * Setup tasks
	 */
	@Before
	public void setup() {
		
	}

	@Test
	public void shouldCreateDefaultConfigurationService() throws Exception {
		ConfigurationService confServ = ConfigurationServiceFactory.getConfigurationService();
		Assert.assertNotNull(confServ);
	}
	
	@Test
	public void shouldCreateSpecificConfigurationService() throws Exception {
		ConfigurationService confServ = ConfigurationServiceFactory.getConfigurationService( "configuration" );
		Assert.assertNotNull(confServ);
	}
	
	@Test
	public void shouldNotCreateSpecificConfigurationWhenInvalidService() throws Exception {
		ConfigurationService confServ = null;
		try {
			confServ = ConfigurationServiceFactory.getConfigurationService( "invalid_conf" );
		} catch ( Exception exc ) {
			Assert.assertTrue( exc instanceof InvalidConfigurationServiceException );
		}
		Assert.assertNull(confServ);
	}
	
	@Test
	public void shouldReturnDefaultWhenCategoryNotFound() throws Exception {
		ConfigurationService confServ = ConfigurationServiceFactory.getConfigurationService( "specific_category" );
		ConfigurationService defaultConfServ = ConfigurationServiceFactory.getConfigurationService();
		Assert.assertEquals(confServ.getClass().getName(), defaultConfServ.getClass().getName());
	}
	
	@Test
	public void shouldPropagateConfigurationReadingToDefaultTenant() throws Exception {
		ConfigurationService confServ = ConfigurationServiceFactory.getConfigurationService();
		String strAppName = confServ.get("app_name");
		Assert.assertEquals( "Content Bowl", strAppName);
	}
}
