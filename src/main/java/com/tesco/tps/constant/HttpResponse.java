package com.tesco.tps.constant;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tesco.tps.core.dto.ResponseDto;
import com.tesco.tps.core.exception.TPSResourceNotFoundException;

/**
 * Http Responses.
 * 
 * @author BW48
 *
 */
public class HttpResponse {

	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	public static final String INVALID_REQUEST = "Invalid request";

	public static Response ok(Object entity) {
		// field validation
		if (entity instanceof ResponseDto) {
			if (((ResponseDto<?>) entity).getContent() == null
					&& (((ResponseDto<?>) entity).getTotal() != null
							|| ((ResponseDto<?>) entity).getPageNumber() != null
							|| ((ResponseDto<?>) entity).getPageSize() != null
							|| ((ResponseDto<?>) entity).getTotalPages() != null)
					|| ((ResponseDto<?>) entity).getTotalElements() != null) {
				return Response.status(HttpServletResponse.SC_OK).entity(entity).build();
			}
		}
		// field validation ends
		if (entity instanceof ResponseDto) {
			if (((ResponseDto<?>) entity).getContent() == null || ((ResponseDto<?>) entity).getContent().isEmpty()) {
				throw new TPSResourceNotFoundException("Resource Not found");
			}
		}
		if (entity == null || (entity instanceof Collection && ((Collection<?>) entity).isEmpty())) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		} else if (entity instanceof ResponseDto && ((ResponseDto<?>) entity).getContent() != null
				&& ((ResponseDto<?>) entity).getMissingSet() != null
				&& !((ResponseDto<?>) entity).getMissingSet().isEmpty()) {
			return Response.status(207).entity(entity).build();
		} else {
			return Response.status(HttpServletResponse.SC_OK).entity(entity).build();
		}
	}

	public static Response ok(Object entity, MediaType contentType) {
		return getResponse(entity, HttpServletResponse.SC_OK, contentType, 0, 0);
	}

	public static Response okWithoutBody() {
		return Response.status(HttpServletResponse.SC_OK).build();
	}

	public static Response noContent() {
		return Response.status(HttpServletResponse.SC_NO_CONTENT).build();
	}

	public static Response created(String location) {
		return Response.status(HttpServletResponse.SC_CREATED).header("Location", location).build();
	}

	public static Response notFound(String message) {
		return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("{\"Message\":\"" + message + "\"}").build();
	}

	public static Response notFoundWithoutMessage() {
		return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
	}

	public static Response badRequestWithouMessage() {
		return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
	}

	public static Response badRequest() {
		return Response.status(HttpServletResponse.SC_BAD_REQUEST)
				.entity(String.format("{\"Error\":\"%s\"}", INVALID_REQUEST)).build();
	}

	public static Response serverError() {
		return Response.serverError().entity(String.format("{\"Error\":\"%s\"}", INTERNAL_SERVER_ERROR)).build();
	}

	public static Response requestNotAllowed(String message) {
		return Response.status(HttpServletResponse.SC_NOT_ACCEPTABLE).entity("{\"Message\":\"" + message + "\"}")
				.build();
	}

	public static Response invalidJson() {
		return Response.status(422).build();
	}

	private static Response getResponse(final Object entity, final int status, MediaType contentType, int maxage,
			int smaxage) {
		HashMap<String, String> charsetMap = new HashMap<String, String>();
		CacheControl control = new CacheControl();
		control.setMaxAge(maxage);
		control.setSMaxAge(smaxage);
		control.setPrivate(false);
		charsetMap.put("charset", "UTF-8");
		String type = contentType.getType();
		String subtype = contentType.getSubtype();
		MediaType MediaTypeObj = new MediaType(type, subtype, charsetMap);
		if (!(status == 200)) {
			return Response.status(status).entity(entity).language(Locale.UK).type(MediaTypeObj).build();
		}
		return Response.ok(entity).language(Locale.UK).type(MediaTypeObj)
				.header("Cache-Control", "public, ".concat(control.toString())).build();
	}

}
