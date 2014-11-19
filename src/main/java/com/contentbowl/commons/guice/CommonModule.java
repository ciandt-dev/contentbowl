package com.contentbowl.commons.guice;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * Guice module responsible for binding all the resources.
 * 
 * @author Daniel Viveiros
 */
public class CommonModule extends ServletModule {

	@Override
	protected void configureServlets() {
		
		Map<String, String> initParams = new HashMap<String, String>();
		
		/*
		bind(CommonResource.class);
		bind(GreetingDAO.class).to(ObjectifyGreetingDAO.class);
		*/
		filter("/api/*").through(GuiceContainer.class, initParams);
	}
}
