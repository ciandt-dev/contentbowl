package com.contentbowl.commons.tenant;

public interface TenantService {
	
	/** Default tenant */
	public static final String DEFAULT_TENANT = "default";
	
	/**
	 * Returns the current tenant. If none is set, it returns the default tenant.
	 */
	public String currentTenant();

}
