package com.contentbowl.commons.configuration;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.contentbowl.commons.tenant.TenantService;
import com.google.inject.Inject;

/**
 * Configuration services that uses a relational database (such as CloudSQL) to get a configuration property 
 * @author Daniel Viveiros
 */
public class DatabaseConfigurationServiceImpl extends AbstractConfigurationService {
	
	@Inject
	private TenantService tenantService;
	
	@Inject
	private ConfigurationValueDAO confValueDAO;

	@Override
	public String get(String key) {
		
		//gets the current namespace
		String tenant = tenantService.currentTenant();
		String value = null;
		
		try {
			value = confValueDAO.findConfigurationValue(key, tenant);
			
			//if not found for current tenant, try to find the configuration in the default namespace
			if (StringUtils.isEmpty(value)) {
				value = confValueDAO.findConfigurationValue(key, TenantService.DEFAULT_TENANT);
			}
		} catch ( SQLException exc ) {
			throw new RuntimeException( "Error reading configuration from database: key = " + key +
					", tenant = " + tenant, exc );
		}
		
		return value;
	}
	

}
