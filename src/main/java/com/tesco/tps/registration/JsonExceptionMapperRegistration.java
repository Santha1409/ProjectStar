package com.tesco.tps.registration;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.google.inject.Injector;
import com.tesco.tps.cdi.interfaces.RegistrationBinder;
import com.tesco.tps.constant.HttpResponse;
import com.tesco.tps.core.annotation.Registration;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;


@Registration
public class JsonExceptionMapperRegistration implements RegistrationBinder {
	private static final org.slf4j.Logger logger=LoggerFactory.getLogger(JsonExceptionMapperRegistration.class);
	static class JsonExceptionMapper implements ExceptionMapper<com.fasterxml.jackson.core.JsonParseException> {

		@Override
		public Response toResponse(JsonParseException exception) {
			logger.error("Invalid Json Format:JsonParseException");
			return HttpResponse.invalidJson();
		}

	}

	static class JasonMappingException implements ExceptionMapper<com.fasterxml.jackson.databind.JsonMappingException> {

		@Override
		public Response toResponse(JsonMappingException exception) {
			logger.error("Invalid Json Format:JsonParseException");
			return HttpResponse.invalidJson();
			
		}

	}

	static class JsonPropertyException
			implements ExceptionMapper<com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException> {

		@Override
		public Response toResponse(UnrecognizedPropertyException exception) {
			logger.error("Invalid Json Format:JsonParseException");
			return HttpResponse.invalidJson();
		}

	}

	static class IllegalArgumentException implements ExceptionMapper<java.lang.IllegalArgumentException> {

		@Override
		public Response toResponse(java.lang.IllegalArgumentException exception) {
			logger.error("Invalid Json Format:JsonParseException");
			return HttpResponse.invalidJson();
		}

	}

	public void register(Injector injector, Configuration configuration, Environment environment) {
		environment.jersey().register(JsonExceptionMapper.class);
		environment.jersey().register(JsonPropertyException.class);
		environment.jersey().register(JasonMappingException.class);
		environment.jersey().register(IllegalArgumentException.class);
	}

}
