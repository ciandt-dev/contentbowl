package com.contentbowl.commons.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * Implementation of configuration services that uses a ResourceBundle as the source of
 * data.
 *  
 * @author Daniel Viveiros
 */
class BundleConfigurationServiceImpl implements ConfigurationService {
	
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
	public String get(String key, String defaultValue) {
		String value = null;
		
		try {
			value = bundle.getString(key); 
		} catch ( MissingResourceException exc ) {
			value = defaultValue;
		}
		
		return value;
	}

	@Override
	public int getInt(String key) {
		String strValue = this.get(key);
		return Integer.parseInt(strValue);
	}

	@Override
	public int getInt(String key, int defaultValue) {
		String strValue = this.get(key, String.valueOf(defaultValue));
		return Integer.parseInt(strValue);
	}

	@Override
	public boolean getBoolean(String key) {
		String strValue = this.get(key);
		return Boolean.parseBoolean(strValue);
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		String strValue = this.get(key, String.valueOf(defaultValue));
		return Boolean.parseBoolean(strValue);
	}

	@Override
	public List<String> getValues(String key) {
		String value = this.get(key);
		List<String> result = new ArrayList<String>();
        
        if (value != null) {
            StringTokenizer strToken = new StringTokenizer(value, ",");
            String str = null;
            while (strToken.hasMoreTokens()) {
                str = strToken.nextToken();
                result.add(str.trim());
            }
        }
        
        return result; 
	}
	
	@Override
    public void setCategory(String category) {
		this.bundle = ResourceBundle.getBundle( category );
	}

}
