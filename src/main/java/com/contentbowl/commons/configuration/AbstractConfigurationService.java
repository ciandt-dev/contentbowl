package com.contentbowl.commons.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Abstract class that defines the common behavior for configuration services
 * @author Daniel Viveiros
 */
public abstract class AbstractConfigurationService implements ConfigurationService {
	
	protected String category;

	@Override
	public String get(String key, String defaultValue) {
		String value = null;
		
		try {
			value = this.get(key); 
		} catch ( ConfigurationRequiredException exc ) {
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
		this.category = category;
	}

}
