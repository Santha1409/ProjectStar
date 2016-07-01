package com.tesco.tps.swagger;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Charsets;
import com.tesco.tps.core.annotation.Resource;

import io.dropwizard.views.View;
import io.federecio.dropwizard.swagger.Constants;

/**
 * @author a483 Rabindra 2 Sep 2015 06:52:06
 * 
 */
@Path("/docs")
@Produces(MediaType.TEXT_HTML)
@Resource
public class SwaggerResource {
	private final String urlPattern;

	public SwaggerResource() {
		this.urlPattern = "";
	}

	public SwaggerResource(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	@GET
	public CustomSwaggerView get() {
		return new CustomSwaggerView(urlPattern);
	}

	public class CustomSwaggerView extends View {
		private final String swaggerAssetsPath;
		private final String contextPath;

		protected CustomSwaggerView(String urlPattern) {
			super("index.ftl", Charsets.UTF_8);

			if (urlPattern.equals("/")) {
				swaggerAssetsPath = Constants.SWAGGER_URI_PATH;
			} else {
				swaggerAssetsPath = urlPattern + Constants.SWAGGER_URI_PATH;
			}

			if (urlPattern.equals("/")) {
				contextPath = "";
			} else {
				contextPath = urlPattern;
			}
		}

		public String getSwaggerAssetsPath() {
			return swaggerAssetsPath;
		}

		/**
		 * Returns the path with with which all requests made by Swagger's UI to
		 * Resources need to be prefixed
		 */
		public String getContextPath() {
			return contextPath;
		}
	}

}
