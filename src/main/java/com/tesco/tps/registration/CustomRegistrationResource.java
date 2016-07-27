package com.tesco.tps.registration;

import com.google.inject.Injector;
import com.tesco.tps.cdi.interfaces.RegistrationBinder;
import com.tesco.tps.configuration.CostAmendConfiguration;
import com.tesco.tps.core.annotation.Registration;
import com.tesco.tps.service.UserDetailsService;
import com.tesco.tps.service.impl.UserDetailsServiceImpl;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 * @author a483 Rabindra 6 May 2016 07:58:18
 * 
 */
@Registration
public class CustomRegistrationResource implements RegistrationBinder {
	@Override
	public void register(Injector injector, Configuration configuration, Environment arg2) {
		CostAmendConfiguration config = (CostAmendConfiguration) configuration;
		UserDetailsServiceImpl detailsService = (UserDetailsServiceImpl) injector.getInstance(UserDetailsService.class);
		detailsService.setUrl(config.getUsermanagementUrl());
	}

}
