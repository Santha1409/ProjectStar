package com.tesco.tps.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;

import com.tesco.tps.auth.support.service.v2.UserDetails;
import com.tesco.tps.service.UserDetailsService;
import com.tesco.tps.core.annotation.Service;
import com.tesco.tps.core.constant.ContentType;
import com.tesco.tps.core.model.HttpRestResponse;
import com.tesco.tps.core.service.HttpRestService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private String url;
	private HttpRestService restService;

	@Inject
	public UserDetailsServiceImpl(HttpRestService restService) {
		this.restService = restService;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public UserDetails getUserDetails(String uuid) {
		Map<String, String> headers = new HashMap<>(1);
		headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.value());
		headers.put("X-Client-Id", "Contract Service");
		HttpRestResponse<UserDetails> response = restService.getEntity(getUrl(), UserDetails.class, headers);
		if (response.getStatusCode() == 200) {
			return response.getContent();
		}
		return null;
	}

	@Override
	public UserDetails getUserDetailsBuAccessToken(String token) {
		Map<String, String> headers = new HashMap<>(1);
		headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.value());
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		headers.put("X-Client-Id", "Contract Service");
		System.out.println("URL-"+getUrl());
		System.out.println("headers-"+headers.toString());
		HttpRestResponse<UserDetails> response = restService.getEntity(getUrl(), UserDetails.class, headers);
		System.out.println("Is response Null -"+response);
		System.out.println("response-"+response.getStatusCode());
		if (response.getStatusCode() == 200) {
			return response.getContent();
		}
		return null;
	}

}