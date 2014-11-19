package com.contentbowl.commons.configuration;

/**
 * Exception thrown when it's impossible to create a configuration service
 * 
 * @author Daniel Viveiros
 */
public class InvalidConfigurationServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public InvalidConfigurationServiceException( String category, Exception innerExc ) {
		super( "It was not possible to create a new ConfigurationService for category = " + category, innerExc );
	}

}
