package com.tesco.tps.secure;

import java.security.Principal;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.tesco.tps.cdi.interfaces.RegistrationBinder;
import com.tesco.tps.configuration.CostAmendConfiguration;
import com.tesco.tps.service.UserDetailsService;
import com.tesco.tps.core.annotation.Registration;

import io.dropwizard.Configuration;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Environment;

/**
 * @author A483 Rabindra 28 Sep 2015 07:22:43
 * 
 */
@Registration
public class SimpleAuthenticatorRegistar implements RegistrationBinder {

	static class SimpleAuthenticator extends AbstractSimpleAuthenticator implements Authenticator<String, Principal> {
		@Inject
		private UserDetailsService userDetailsService;

		@Override
		public Optional<Principal> authenticate(String token) throws AuthenticationException {
			Principal userDetails = getUserDetailsV2(token);
			return userDetails == null ? Optional.absent() : Optional.of(userDetails);

		}

		@Override
		protected UserDetailsService getUserDetailsService() {
			return userDetailsService;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tesco.tps.cdi.interfaces.RegistrationBinder#register(com.google.
	 * inject .Injector, io.dropwizard.Configuration,
	 * io.dropwizard.setup.Environment)
	 */
	@Override
	public void register(Injector injector, Configuration configuration, Environment environment) {
		CachingAuthenticator<String, Principal> cachingAuthenticator = new CachingAuthenticator<>(environment.metrics(),
				injector.getInstance(SimpleAuthenticator.class),
				((CostAmendConfiguration) configuration).getAuthenticationCachePolicy());

		environment.jersey().register(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<Principal>()
				.setAuthenticator(cachingAuthenticator).setPrefix("Bearer").setRealm("OAUTH_TPS").buildAuthFilter()));
		environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Principal.class));

	}

}
