package com.contentbowl.commons.guice;

import java.util.ArrayList;
import java.util.List;

import com.contentbowl.commons.configuration.ConfigurationService;
import com.contentbowl.commons.configuration.ConfigurationServiceFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Utility class for injection behavior
 * 
 * @author Daniel Viveiros
 */
public class InjectionUtils {
	
	private static Injector injector;
	private static ConfigurationService guiceConfService;
	
	static {
		try {
			guiceConfService = ConfigurationServiceFactory.getConfigurationService("guice");
		} catch ( Exception exc ) {
			exc.printStackTrace();
		}
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

}
