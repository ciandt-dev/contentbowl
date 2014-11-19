package com.contentbowl.commons.persistence;

/**
 * Exception thrown when it's not possible to establish a connection to the database
 * @author Daniel Viveiros
 */
public class CouldNotCreateConnectionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param className JDBC driver class name
	 * @param url JDBC url used to create the connection
	 */
	public CouldNotCreateConnectionException( String className, String url, Exception innerExc ) {
		super( "Could not create a database connection for className = " + className + " and jdbc_url = "
				+ url, innerExc);
	}

}
