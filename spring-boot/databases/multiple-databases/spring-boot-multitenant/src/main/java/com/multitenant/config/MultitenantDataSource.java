package com.multitenant.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultitenantDataSource extends AbstractRoutingDataSource {

	private static final Logger LOGGER = LoggerFactory.getLogger(MultitenantDataSource.class);

	@Override
	protected String determineCurrentLookupKey() {
		LOGGER.info("CurrentTenant: {}", TenantContext.getCurrentTenant());
		return TenantContext.getCurrentTenant();
	}

}
