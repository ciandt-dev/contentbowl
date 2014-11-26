package com.contentbowl.commons.tenant;

import org.apache.commons.lang3.StringUtils;

import com.google.appengine.api.NamespaceManager;
import com.google.inject.Singleton;

/**
 * TenantService implementation
 * 
 * @author Daniel Viveiros
 */
@Singleton
public class TenantServiceImpl implements TenantService {

	@Override
	public String currentTenant() {
		//gets the current namespace
		String tenant = null;
		tenant = NamespaceManager.get();
		
		/*
		try {
			
		} catch ( NullPointerException exc ) { //that happens during unit test
			tenant = TenantService.DEFAULT_TENANT;
		}
		*/
		
		if ( StringUtils.isEmpty(tenant) ) {
			tenant = TenantService.DEFAULT_TENANT;
		}
		return tenant;
	}

}
