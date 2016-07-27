package com.tesco.tps.registration;

import javax.validation.Validation;
import javax.validation.Validator;

import com.google.inject.Injector;
import com.tesco.tps.cdi.factory.GuiceConstraintValidatorFactory;
import com.tesco.tps.cdi.interfaces.RegistrationBinder;
import com.tesco.tps.core.annotation.Registration;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 * @author a483 Rabindra 15 Oct 2015 14:36:22
 * 
 */
@Registration
public class ValidatorRegistration implements RegistrationBinder {

	@Override
	public void register(Injector injector, Configuration configuration, Environment environment) {
		Validator validator = Validation.buildDefaultValidatorFactory().usingContext()
				.constraintValidatorFactory(injector.getInstance(GuiceConstraintValidatorFactory.class)).getValidator();
		environment.setValidator(validator);
	}

}
