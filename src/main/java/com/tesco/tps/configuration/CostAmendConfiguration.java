package com.tesco.tps.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.cache.CacheBuilderSpec;
import com.tesco.tps.cdi.config.TradingPartnerCdiConfig;

import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * Read configuration details from classpath *.yml file
 * 
 * @author a483 Rabindra 27 Aug 2015
 * 
 */
public class CostAmendConfiguration extends Configuration {

	
	@JsonProperty("tpsConfig")
	private TradingPartnerCdiConfig tpsConfig;

	@JsonProperty("swagger")
	public SwaggerBundleConfiguration swaggerBundleConfiguration;

	@JsonProperty("basePackages")
	public String basePackages;

	@JsonProperty("httpClient")
	private HttpClientConfiguration httpClientConfig;

	

	@JsonProperty("clientid")
	private String clientid;
	
	@JsonProperty("granttype")
	private String granttype;
	
	@JsonProperty("scope")
	private String scope;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;

	@JsonProperty("identityURL")
	private String identityURL;
	
	@JsonProperty("TPSURL")
	private String TPSURL;
	
	@JsonProperty("TPSUSERGroupURL")
	private String TPSUSERGroupURL;
	
	@JsonProperty("TUS_CLIENT_ID")
	private String TUS_CLIENT_ID;
	@JsonProperty("tradedunit.x-client-id")
	private String tuClientId;
	
	@JsonProperty("tradedunit.cost.x-client-id")
	private String tuCostClientId;
	
	@JsonProperty("PARENT_GROUP_NAME")
	private String PARENT_GROUP_NAME;
	
	@JsonProperty("GROUP_NAME")
	private String GROUP_NAME;

	public TradingPartnerCdiConfig getTpsConfig() {
		return tpsConfig;
	}

	public void setTpsConfig(TradingPartnerCdiConfig tpsConfig) {
		this.tpsConfig = tpsConfig;
	}

	public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
		return swaggerBundleConfiguration;
	}

	public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {
		this.swaggerBundleConfiguration = swaggerBundleConfiguration;
	}

	public String getBasePackages() {
		return basePackages;
	}

	public void setBasePackages(String basePackages) {
		this.basePackages = basePackages;
	}

	public HttpClientConfiguration getHttpClientConfig() {
		return httpClientConfig;
	}

	public void setHttpClientConfig(HttpClientConfiguration httpClientConfig) {
		this.httpClientConfig = httpClientConfig;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getGranttype() {
		return granttype;
	}

	public void setGranttype(String granttype) {
		this.granttype = granttype;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentityURL() {
		return identityURL;
	}

	public void setIdentityURL(String identityURL) {
		this.identityURL = identityURL;
	}

	public String getTPSURL() {
		return TPSURL;
	}

	public void setTPSURL(String tPSURL) {
		TPSURL = tPSURL;
	}

	public String getTPSUSERGroupURL() {
		return TPSUSERGroupURL;
	}

	public void setTPSUSERGroupURL(String tPSUSERGroupURL) {
		TPSUSERGroupURL = tPSUSERGroupURL;
	}

	public String getTUS_CLIENT_ID() {
		return TUS_CLIENT_ID;
	}

	public void setTUS_CLIENT_ID(String tUS_CLIENT_ID) {
		TUS_CLIENT_ID = tUS_CLIENT_ID;
	}

	public String getTuClientId() {
		return tuClientId;
	}

	public void setTuClientId(String tuClientId) {
		this.tuClientId = tuClientId;
	}

	public String getTuCostClientId() {
		return tuCostClientId;
	}

	public void setTuCostClientId(String tuCostClientId) {
		this.tuCostClientId = tuCostClientId;
	}

	public String getPARENT_GROUP_NAME() {
		return PARENT_GROUP_NAME;
	}

	public void setPARENT_GROUP_NAME(String pARENT_GROUP_NAME) {
		PARENT_GROUP_NAME = pARENT_GROUP_NAME;
	}

	public String getGROUP_NAME() {
		return GROUP_NAME;
	}

	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}
	
	

}
