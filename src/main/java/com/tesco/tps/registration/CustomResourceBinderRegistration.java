package com.tesco.tps.registration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Binder;
import com.google.inject.matcher.Matchers;
import com.tesco.tps.auth.support.annotation.v2.Secured;
import com.tesco.tps.auth.support.service.v2.SecurityAwareInterceptor;
import com.tesco.tps.configuration.CostAmendConfiguration;
import com.tesco.tps.core.annotation.ResourceBinder;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 * @author a483 Rabindra 7 Oct 2015 07:41:32
 * 
 */
@ResourceBinder
public class CustomResourceBinderRegistration implements com.tesco.tps.cdi.interfaces.ResourceBinder {
	private ObjectMapper objectMapper = null;

	private void createObject(CostAmendConfiguration config, Environment env) {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tesco.tps.cdi.interfaces.ResourceBinder#bind(com.google.inject.Binder
	 * , io.dropwizard.Configuration, io.dropwizard.setup.Environment)
	 */
	@Override
	public void bind(Binder binder, Configuration config, Environment env) {
		createObject((CostAmendConfiguration) config, env);
		binder.bind(ObjectMapper.class).toInstance(objectMapper);
		binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Secured.class), new SecurityAwareInterceptor());
	}

}
