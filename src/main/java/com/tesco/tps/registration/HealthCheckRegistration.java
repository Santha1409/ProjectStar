package com.tesco.tps.registration;

import com.google.inject.Injector;
import com.tesco.tps.cdi.interfaces.RegistrationBinder;
import com.tesco.tps.healthcheck.CouchbaseHealthCheck;
import com.tesco.tps.core.annotation.Registration;
import com.tesco.tps.couchbase.CouchbaseResource;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;


@Registration
public class HealthCheckRegistration implements RegistrationBinder {

	@Override
	public void register(Injector injector, Configuration config, Environment env) {
		env.healthChecks().register("couchbase-health-check",
				new CouchbaseHealthCheck(injector.getInstance(CouchbaseResource.class).getAsyncCouchbaseWrapper()));
	}

}
