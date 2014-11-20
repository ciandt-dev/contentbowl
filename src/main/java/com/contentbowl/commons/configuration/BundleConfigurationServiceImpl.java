package com.contentbowl.commons.configuration;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Implementation of configuration services that uses a ResourceBundle as the source of
 * data.
 *  
 * @author Daniel Viveiros
 */
public class BundleConfigurationServiceImpl extends AbstractConfigurationService {
	
	/** Resource Bundle to be used as the source of data */
	private ResourceBundle bundle;
	
	/**
	 * Constructor
	 */
	public BundleConfigurationServiceImpl() {
	}
	

	@Override
	public String get(String key) {
		String value = null;
		
		try {
			value = bundle.getString(key); 
		} catch ( MissingResourceException exc ) {
			throw new ConfigurationRequiredException(key);
		}
		
		return value; 
	}
	
	@Override
    public void setCategory(String category) {
		super.setCategory(category);
		this.bundle = ResourceBundle.getBundle( category );
	}

}
