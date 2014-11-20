package com.contentbowl.commons.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.contentbowl.commons.persistence.AbstractCloudSQLDAO;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.inject.Singleton;

/**
 * DAO for configuration values
 * @author Daniel Viveiros
 */
@Singleton
public class ConfigurationValueDAO extends AbstractCloudSQLDAO {
	
	private static final String CACHE_KEY = "cache.ConfigurationValueDAO";
	
	/**
	 * Read a configuration value for a specific tenant
	 */
	/**
	 * Gets the configuration for a specific tenant
	 */
	public String findConfigurationValue( String key, String tenant ) throws SQLException {
		String strResult = null;
		
		//try to find the configuration in the cache
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService( ConfigurationValueDAO.CACHE_KEY );
		String cacheKey = key + "|" + tenant;
		strResult = (String) cache.get( cacheKey );
		if (strResult != null) {
			return strResult;
		}
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = prepareStatement("configuration_query");
			pstmt.setString(1, key);
			pstmt.setString(2, tenant);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				strResult = rset.getString(1);
				cache.put( cacheKey, strResult);
			}
		} finally {
			Connection conn = pstmt.getConnection();
			if (conn != null) {
				conn.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (rset != null) {
				rset.close();
			}
		}
		
		return strResult;
	}

}
