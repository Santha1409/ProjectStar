package com.tesco.tps.registration;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.tesco.tps.cdi.interfaces.RegistrationBinder;
import com.tesco.tps.core.annotation.Registration;
import com.tesco.tps.core.exception.TPSAuthenticationException;
import com.tesco.tps.core.exception.TPSAuthorizationException;
import com.tesco.tps.core.exception.TPSBadRequestException;
import com.tesco.tps.core.exception.TPSInternalServerErrorException;
import com.tesco.tps.core.exception.TPSOperationNotSupportedException;
import com.tesco.tps.core.exception.TPSResourceNotFoundException;
import com.tesco.tps.core.exception.TPSValidationException;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 * @author a483 Rabindra 14 Sep 2015 11:13:54
 * 
 */
@Registration
public class ExceptionMapperRegistration implements RegistrationBinder {
	private static final org.slf4j.Logger logger=LoggerFactory.getLogger(JsonExceptionMapperRegistration.class);
	@Override
	public void register(Injector injector, Configuration config, Environment environment) {
		environment.jersey().register(ConstraintViolationExceptionMapper.class);
		environment.jersey().register(TPSValidationExceptionMapper.class);
		environment.jersey().register(TPSAuthenticationExceptionMapper.class);
		environment.jersey().register(TPSAuthorizationExceptionMapper.class);
		environment.jersey().register(TPSBadRequestExceptionMapper.class);
		environment.jersey().register(TPSResourceNotFoundExceptionMapper.class);
		environment.jersey().register(TPSInternalServerErrorExceptionMapper.class);
		environment.jersey().register(TPSOperationNotSupportedExceptionMapper.class);
		environment.jersey().register(ValidationExceptionMapper.class);
	}

	static class TPSValidationExceptionMapper implements ExceptionMapper<TPSValidationException> {

		@Override
		public Response toResponse(TPSValidationException exception) {
			logger.error("TPSValidationException[" + exception.getMessage() + "]");
			return Response.status(400).build();
		}

	}

	static class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
		@Override
		public Response toResponse(ConstraintViolationException exception) {
			logger.error("ConstraintViolationException[" + exception.getMessage() + "]");
			exception.getConstraintViolations().forEach(val ->logger
					.error("[" + val.getInvalidValue() + "::" + val.getMessage() + "::" + val.getPropertyPath() + "]"));
			return Response.status(400).build();
		}

	}

	static class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

		@Override
		public Response toResponse(ValidationException exception) {
			logger.error("ValidationException[" + exception.getMessage() + "]");
			return Response.status(400).build();
		}

	}

	static class TPSAuthenticationExceptionMapper implements ExceptionMapper<TPSAuthenticationException> {
		@Override
		public Response toResponse(TPSAuthenticationException exception) {
			logger.error("TPSAuthenticationException[" + exception.getMessage() + "]");
			return Response.status(401).build();
		}

	}

	static class TPSAuthorizationExceptionMapper implements ExceptionMapper<TPSAuthorizationException> {

		@Override
		public Response toResponse(TPSAuthorizationException exception) {
			logger.error("TPSAuthorizationException[" + exception.getMessage() + "]");
			return Response.status(403).build();
		}

	}

	static class TPSBadRequestExceptionMapper implements ExceptionMapper<TPSBadRequestException> {

		@Override
		public Response toResponse(TPSBadRequestException exception) {
			logger.error("TPSBadRequestException[" + exception.getMessage() + "]");
			return Response.status(400).build();
		}

	}

	static class TPSInternalServerErrorExceptionMapper implements ExceptionMapper<TPSInternalServerErrorException> {

		@Override
		public Response toResponse(TPSInternalServerErrorException exception) {
			logger.error("TPSInternalServerErrorException[" + exception + "]");
			return Response.status(500).build();
		}

	}

	static class TPSResourceNotFoundExceptionMapper implements ExceptionMapper<TPSResourceNotFoundException> {

		@Override
		public Response toResponse(TPSResourceNotFoundException exception) {
			logger.error("TPSResourceNotFoundException[" + exception.getMessage() + "]");
			return Response.status(404).build();
		}

	}

	static class TPSOperationNotSupportedExceptionMapper implements ExceptionMapper<TPSOperationNotSupportedException> {

		@Override
		public Response toResponse(TPSOperationNotSupportedException exception) {
			logger.error("TPSOperationNotSupportedException[" + exception.getMessage() + "]");
			return Response.status(500).build();
		}

	}

}
