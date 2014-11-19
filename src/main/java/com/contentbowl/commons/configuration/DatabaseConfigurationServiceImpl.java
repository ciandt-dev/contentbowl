package com.contentbowl.commons.configuration;

import java.util.List;

/**
 * Configuration services that uses a relational database (such as CloudSQL) to get a configuration property 
 * @author Daniel Viveiros
 */
public class DatabaseConfigurationServiceImpl implements ConfigurationService {

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInt(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String key, int defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getBoolean(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getValues(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCategory(String category) {
		// TODO Auto-generated method stub
		
	}

}
