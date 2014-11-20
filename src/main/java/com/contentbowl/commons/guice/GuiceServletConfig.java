package com.contentbowl.commons.guice;

import java.util.ArrayList;
import java.util.List;

import com.contentbowl.commons.configuration.ConfigurationService;
import com.contentbowl.commons.configuration.ConfigurationServiceFactory;
import com.contentbowl.sample.greetings.config.GreetingsModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * ServletConfig that initializes Guice and all its modules
 * 
 * @author Daniel Viveiros
 */
public class GuiceServletConfig extends GuiceServletContextListener {
	
	private static ConfigurationService guiceConfService;
	private static Injector injector;
	
	static {
		guiceConfService = ConfigurationServiceFactory.getConfigurationService("guice");
	}
	
	public static Injector createInjector() {
		if (injector == null) {
			List<String> moduleClasses = guiceConfService.getValues("modules");
			List<Module> lModules = new ArrayList<Module>();
			for (String moduleClass: moduleClasses) {
				try {
					lModules.add((Module) Class.forName(moduleClass).newInstance() );
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException e) {
					throw new RuntimeException( "Error initializing guice injector", e );
				}
			}
			
			injector = Guice.createInjector(lModules);
		}
		
		return injector;
	}

	@Override
	protected Injector getInjector() {
		return GuiceServletConfig.createInjector();
	}
	
}
