package com.contentbowl.commons.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.contentbowl.commons.configuration.ConfigurationService;
import com.contentbowl.commons.configuration.ConfigurationServiceFactory;
import com.google.appengine.api.utils.SystemProperty;

/**
 * Abstract class for DAOs that uses CloudSQL as source of data
 * @author Daniel Viveiros
 */
public abstract class AbstractCloudSQLDAO extends AbstractDAO {
	
	private static final String LOCAL_DATABASE_CONF = "database_local";
	private static final String REMOTE_DATABASE_CONF = "database_remote";
	private static final String QUERY_CONF = "sql_queries";
	
	private static ConfigurationService databaseConfService;
	private static ConfigurationService queryConfService;
	private static Boolean isRemote;
	
	static {
		
		//checks the environment we are running the code
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			// app is running on Google App Engine
			databaseConfService = ConfigurationServiceFactory.getConfigurationService( REMOTE_DATABASE_CONF );
			isRemote = true;
		} else {
			// app is running on localhost
			databaseConfService = ConfigurationServiceFactory.getConfigurationService( LOCAL_DATABASE_CONF );
			isRemote = false;
		}
		
		//query services
		queryConfService = ConfigurationServiceFactory.getConfigurationService(QUERY_CONF);
	}
	
	/**
	 * Creates a connection to the local database
	 */
	protected Connection createConnection() {
		
		String className = databaseConfService.get("jdbc_driver");
		String url = databaseConfService.get("jdbc_url");
		String user = databaseConfService.get("db_user");
		String pwd = databaseConfService.get("db_password");
		
		Connection conn = null;
		
		try {
			Class.forName( className );
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (Exception exc) {
			throw new CouldNotCreateConnectionException(className, url, exc);
		}
		
		return conn;
	}
	
	/**
	 * Prepares a statement
	 * @throws SQLException 
	 */
	protected PreparedStatement prepareStatement( String queryKey ) throws SQLException {
		String strQuery = queryConfService.get(queryKey);
		return createConnection().prepareStatement(strQuery);
	}
	
	/**
	 * Returns if this code is running on GAE or local
	 * @return If this code is running on GAE
	 */
	protected static Boolean isRemote() {
		return isRemote;
	}
}
