package com.tesco.tps.util;

import java.security.Principal;

import org.junit.Ignore;

import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

/**
 * @author a483 Rabindra 9 Dec 2015 05:53:51
 * 
 */
@Ignore
public class TestAuthenticator implements Authenticator<String, Principal> {
	private Principal userDetail;

	public TestAuthenticator(Principal userDetails) {
		this.userDetail = userDetails;
	}

	@Override
	public Optional<Principal> authenticate(String credentials) throws AuthenticationException {
		return Optional.of(userDetail);
	}
}
