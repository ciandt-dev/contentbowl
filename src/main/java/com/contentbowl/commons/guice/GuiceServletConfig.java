package com.contentbowl.commons.guice;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * ServletConfig that initializes Guice and all its modules
 * 
 * @author Daniel Viveiros
 */
public class GuiceServletConfig extends GuiceServletContextListener {
	
	@Override
	protected Injector getInjector() {
		return InjectionUtils.createInjector();
	}
	
}
