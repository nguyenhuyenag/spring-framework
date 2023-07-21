package com.multitenant.config;

public class TenantContext {

	private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

	public static String getCurrentTenant() {
		return CURRENT_TENANT.get();
	}

	public static void setCurrentTenant(String tenantName) {
		CURRENT_TENANT.set(tenantName);
	}

	public static void clearTenant() {
		CURRENT_TENANT.remove();
	}

}
