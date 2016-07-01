package com.tesco.tps.swagger;



import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.tesco.tps.core.annotation.Resource;
import com.tesco.tps.util.Logger;
import com.wordnik.swagger.config.FilterFactory;
import com.wordnik.swagger.config.Scanner;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.core.filter.SpecFilter;
import com.wordnik.swagger.core.filter.SwaggerSpecFilter;
import com.wordnik.swagger.jaxrs.Reader;
import com.wordnik.swagger.jaxrs.config.JaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.SwaggerSerializers;
import com.wordnik.swagger.models.Swagger;

/**
 * Swagger document endpoing
 * 
 * @author a483 Rabindra 2 Sep 2015 06:51:22
 * 
 */
@Resource
@Path("/docs-api")
public class ApiListenerResource {
	static boolean initialized = false;
	@Context
	ServletContext context;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListing(@Context Application app, @Context ServletConfig sc, @Context HttpHeaders headers,
			@Context UriInfo uriInfo) {
		Swagger swagger = (Swagger) context.getAttribute("swagger");
		if (!initialized)
			swagger = scan(app, sc);
		if (swagger != null) {
			SwaggerSpecFilter filterImpl = FilterFactory.getFilter();
			if (filterImpl != null) {
				SpecFilter f = new SpecFilter();
				swagger = f.filter(swagger, filterImpl, getQueryParams(uriInfo.getQueryParameters()),
						getCookies(headers), getHeaders(headers));
			}
			return Response.ok().entity(swagger).build();
		} else
			return Response.status(404).build();
	}

	protected Map<String, List<String>> getQueryParams(MultivaluedMap<String, String> params) {
		Map<String, List<String>> output = new HashMap<String, List<String>>();
		if (params != null) {
			for (String key : params.keySet()) {
				List<String> values = params.get(key);
				output.put(key, values);
			}
		}
		return output;
	}

	protected Map<String, String> getCookies(HttpHeaders headers) {
		Map<String, String> output = new HashMap<String, String>();
		if (headers != null) {
			for (String key : headers.getCookies().keySet()) {
				Cookie cookie = headers.getCookies().get(key);
				output.put(key, cookie.getValue());
			}
		}
		return output;
	}

	protected Map<String, List<String>> getHeaders(HttpHeaders headers) {
		Map<String, List<String>> output = new HashMap<String, List<String>>();
		if (headers != null) {
			for (String key : headers.getRequestHeaders().keySet()) {
				List<String> values = headers.getRequestHeaders().get(key);
				output.put(key, values);
			}
		}
		return output;
	}

	protected synchronized Swagger scan(Application app, ServletConfig sc) {
		Swagger swagger = null;
		Scanner scanner = ScannerFactory.getScanner();
		Logger.CAS_CORE.debug("using scanner " + scanner);

		if (scanner != null) {
			SwaggerSerializers.setPrettyPrint(scanner.getPrettyPrint());
			swagger = (Swagger) context.getAttribute("swagger");

			Set<Class<?>> classes = new HashSet<Class<?>>();
			if (scanner instanceof JaxrsScanner) {
				JaxrsScanner jaxrsScanner = (JaxrsScanner) scanner;
				classes = jaxrsScanner.classesFromContext(app, sc);
			} else {
				classes = scanner.classes();
			}
			if (classes != null) {
				Reader reader = new Reader(swagger);
				swagger = reader.read(classes);
				if (scanner instanceof SwaggerConfig)
					swagger = ((SwaggerConfig) scanner).configure(swagger);
				else {
					SwaggerConfig configurator = (SwaggerConfig) context.getAttribute("reader");
					if (configurator != null) {
						Logger.CAS_CORE.debug("configuring swagger with " + configurator);
						configurator.configure(swagger);
					} else
						Logger.CAS_CORE.debug("no configurator");
				}
				context.setAttribute("swagger", swagger);
			}
		}
		initialized = true;
		return swagger;
	}
}
