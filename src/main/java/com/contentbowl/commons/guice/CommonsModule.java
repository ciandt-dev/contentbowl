package com.contentbowl.commons.guice;

import com.contentbowl.commons.configuration.BundleConfigurationServiceImpl;
import com.contentbowl.commons.configuration.ConfigurationValueDAO;
import com.contentbowl.commons.configuration.DatabaseConfigurationServiceImpl;
import com.contentbowl.commons.tenant.TenantService;
import com.contentbowl.commons.tenant.TenantServiceImpl;
import com.google.inject.servlet.ServletModule;

/**
 * Guice module responsible for binding all the resources.
 * 
 * @author Daniel Viveiros
 */
public class CommonsModule extends ServletModule {

	@Override
	protected void configureServlets() {
		
		bindConfigurationService();
		bindTenantService();
		
		/*
		Map<String, String> initParams = new HashMap<String, String>();
		filter("/api/*").through(GuiceContainer.class, initParams);
		*/
	}
	
	/**
	 * Configuration mechanism
	 */
	private void bindConfigurationService() {
		bind(ConfigurationValueDAO.class);
		bind(BundleConfigurationServiceImpl.class);
		bind(DatabaseConfigurationServiceImpl.class);
	}
	
	/**
	 * Tenant mechanism
	 */
	private void bindTenantService() {
		bind(TenantService.class).to(TenantServiceImpl.class);
	}
}
