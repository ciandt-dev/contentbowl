package com.contentbowl.commons.configuration;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.contentbowl.commons.test.AbstractTestCase;

/**
 * Tests ConfigurationValueDAO
 * 
 * @author Daniel Viveiros
 */
public class ConfigurationValueDAOTest extends AbstractTestCase {
	
	private ConfigurationValueDAO confValueDAO;
	
	@Before
	public void setup() {
		confValueDAO = getInstance( ConfigurationValueDAO.class );
	}
	
	@Test
	public void shouldReadValueFromDatabase() throws Exception {
		String strAppName = confValueDAO.findConfigurationValue("app_name", "default");
		Assert.assertEquals( "Content Bowl", strAppName);
	}

}
