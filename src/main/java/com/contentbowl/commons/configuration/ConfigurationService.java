package com.contentbowl.commons.configuration;

import java.util.List;

/**
 * Common interface for configuration services. It allows you to easily create configurations
 * to be used inside you application
 * 
 * @author Daniel Viveiros
 */
public interface ConfigurationService {
	
	/**
     * Returns the value of a property. If not found, returns null or throws ConfigurationRequiredException if
     * the configuration is required.
     * 
     * @param key The property key
     * @return The property value
     */
    public String get(String key);
    
    /**
     * Returns the value of a property. If not found, returns the default value.
     * 
     * @param key The property key
     * @param defaultValue Default value to be returned if the property is not found
     * @return
     */
    public String get(String key, String defaultValue);
    
    /**
     * Returns the value of a property. If not found, returns null or throws ConfigurationRequiredException if
     * the configuration is required.
     * 
     * @param key The property key
     * @return The property value
     */
    public int getInt(String key);
    
    /**
     * Returns the value of a property. If not found, returns the default value.
     * 
     * @param key The property key
     * @param defaultValue Default value to be returned if the property is not found
     * @return
     */
    public int getInt(String key, int defaultValue);
    
    /**
     * Returns the value of a property. If not found, returns null or throws ConfigurationRequiredException if
     * the configuration is required.
     * 
     * @param key The property key
     * @return The property value
     */
    public boolean getBoolean(String key);
    
    /**
     * Returns the value of a property. If not found, returns the default value.
     * 
     * @param key The property key
     * @param defaultValue Default value to be returned if the property is not found
     * @return
     */
    public boolean getBoolean(String key, boolean defaultValue);

    /**
     * Returns the values of a specific attribute separated by comma (,).
     * 
     * @param key The property key
     * @return List of property values
     */
    public List<String> getValues(String key);
    
    /**
     * Defines the category
     * 
     * @param category Category to be set
     */
    void setCategory( String category);

}
