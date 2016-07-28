package com.tesco.tps.util;

import java.lang.reflect.Field;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;

import com.tesco.tps.auth.model.UserDetails;
import com.tesco.tps.auth.model.UserDetails.Group;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.testing.junit.ResourceTestRule;

/**
 * @author a483 Rabindra 8 Dec 2015 10:16:29
 * 
 */
public class ResourceTestRuleUtils {
	public Logger logger = Logger.getLogger("[TPS]:[TEST]:");
	private UserDetails userDetails;

	public ResourceTestRuleUtils() {
		userDetails = new UserDetails("1234", "Internal", null, "SuperUser");
		userDetails.setAccessPolicy(new HashMap<>());
		userDetails.setActiveFilters(new ArrayList<>());
		Group group = new Group();
		group.setId("1234");
		group.setGroupName("ADMIN");
		userDetails.setGroup(Arrays.asList(group));
	}

	public Principal getUserDetails() {
		return this.userDetails;
	}

	protected TestAuthenticator getAuthenticator(Principal userDetails) {
		return new TestAuthenticator(userDetails);
	}

	protected <D, T> T getResource(Class<T> cls, List<?> params) {
		T obj = null;
		try {
			obj = cls.newInstance();
			instantiateProperties(obj, cls, params);
		} catch (InstantiationException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public <T, D> ResourceTestRule getResourceTestRule(Class<T> cls, D... params) {
		return getResourceTestRule(null, cls, params);
	}

	public <T, D> ResourceTestRule getResourceTestRule(Object resource) {
		return ResourceTestRule.builder().setTestContainerFactory(new GrizzlyWebTestContainerFactory())
				.addProvider(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<Principal>()
						.setAuthenticator(getAuthenticator(getUserDetails())).setPrefix("Bearer").setRealm("OAUTH_TPS")
						.buildAuthFilter()))
				.addProvider(new AuthValueFactoryProvider.Binder<>(Principal.class)).addResource(resource).build();
	}

	@SuppressWarnings("unchecked")
	public <T, D> ResourceTestRule getResourceTestRule(UserDetails userDetails, Class<T> cls, D... params) {
		if (userDetails != null) {
			this.userDetails = userDetails;
		}
		return ResourceTestRule.builder().setTestContainerFactory(new GrizzlyWebTestContainerFactory())
				.addProvider(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<Principal>()
						.setAuthenticator(getAuthenticator(getUserDetails())).setPrefix("Bearer").setRealm("OAUTH_TPS")
						.buildAuthFilter()))
				.addProvider(new AuthValueFactoryProvider.Binder<>(Principal.class))
				.addResource(getResource(cls, Arrays.asList(params))).build();
	}

	private <D, T> void instantiateProperties(T bean, Class<T> cls, List<?> params) {
		params.parallelStream().forEach(obj -> {
			Field[] fields = cls.getDeclaredFields();
			List<Field> fieldList = Arrays.asList(fields);
			java.util.Optional<Field> opField = fieldList.parallelStream().filter(fild -> checkField(fild, obj))
					.findAny();
			if (opField.isPresent()) {
				opField.get().setAccessible(true);
				setObject(opField.get(), bean, obj);
			}
		});
	}

	private boolean checkField(Field fld, Object obj) {
		if (obj.getClass().getGenericInterfaces() != null && obj.getClass().getGenericInterfaces().length > 0) {
			return fld.getType().equals(obj.getClass().getGenericInterfaces()[0]);
		} else if (obj.getClass().getGenericSuperclass() != null) {
			return fld.getType().equals(obj.getClass().getGenericSuperclass());
		} else {
			return fld.getType().equals(obj.getClass());
		}
	}

	private void setObject(Field field, Object bean, Object value) {
		try {
			field.set(bean, value);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		}
	}
}
