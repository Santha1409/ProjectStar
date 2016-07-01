package com.tesco.tps.config.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.tesco.tps.constant.CostAmendConstants;
import com.tesco.tps.core.annotation.Filter;
import com.tesco.tps.service.impl.TradedUnitValidateUserServiceImpl;

/**
 * @author a483 Rabindra 19 Nov 2015 05:48:26
 * 
 */
@Filter
public class ClientLoggerFilter implements javax.servlet.Filter {

	
	@Inject
	TradedUnitValidateUserServiceImpl tradedUnitValidateUserServiceImpl;
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ClientLoggerFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String clientId = httpRequest.getHeader(CostAmendConstants.CLIENT_ID);
			String  authorization=httpRequest.getHeader("Authorization");
			if (httpRequest.getRequestURI().matches("/v1/admin/isavailable") || httpRequest.getMethod().equals("OPTIONS")) {
				chain.doFilter(request, response);
			} 
		
			else if((httpRequest.getRequestURI().contains("/v1/tradedunits") && (!(httpRequest.getRequestURI().contains("update"))) ) && (httpRequest.getMethod().equals("GET") || httpRequest.getMethod().equals("POST")  ))
			{

				if(authorization != null){
				
				}
				int status=tradedUnitValidateUserServiceImpl.validateAccessGroup(authorization,clientId);
				//status=200;
				if(status==HttpServletResponse.SC_OK)
			
				chain.doFilter(request, response);
				else
				{
					
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					((HttpServletResponse) response).getWriter()
							.print("UNAUTHORISED");

				}
			}
			
			
			
			else {
				/*LOG.info("<< AUDIT :: URI: " + httpRequest.getRequestURI() + " - " + "Method: " + httpRequest.getMethod()
						+ " - " + "ClientId: " + clientId + " - " + "Host: " + httpRequest.getRemoteHost() + " >>");*/
				if (httpRequest.getRequestURI().matches("/docs/*")
						|| httpRequest.getRequestURI().contains("/swagger-static/")
						|| httpRequest.getRequestURI().matches("/favicon.ico")) {
					chain.doFilter(request, response);
				} else {
					if (clientId != null) {
						chain.doFilter(request, response);
						
					} else {
						((HttpServletResponse) response).setStatus(400);
						((HttpServletResponse) response).getWriter()
								.print("Header (X-Client-Id) is required to access this resource.");
					}
				}

			}
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public void destroy() {
	}

}
