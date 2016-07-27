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
	
	@JsonProperty("authenticationCachePolicy")
	private CacheBuilderSpec authenticationCachePolicy;

	public void setAuthenticationCachePolicy(CacheBuilderSpec authenticationCachePolicy) {
		this.authenticationCachePolicy = authenticationCachePolicy;
	}

	public CacheBuilderSpec getAuthenticationCachePolicy() {
		return authenticationCachePolicy;
	}

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

	/*public String getTuClientId() {
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
	}*/

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
	
	// TUS V2 versiom
    @JsonProperty("V2clientid")
    private String V2clientid;
    
    @JsonProperty("V2granttype")
    private String V2granttype;
    
    @JsonProperty("V2scope")
    private String V2scope;
    
    
    @JsonProperty("V2username")
    private String V2username;
    
    
    @JsonProperty("V2password")
    private String V2password;
    
    
    @JsonProperty("V2TPSUSERGroupURL")
    private String V2TPSUSERGroupURL;
    
	@JsonProperty("usermanagementUrl")
	private String usermanagementUrl;

    @JsonProperty("V2groupName")
    private String V2groupName;
    
    
    @JsonProperty("V2roleName")
    private String V2roleName;
    
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

    public String getV2clientid() {
           return V2clientid;
    }

    public void setV2clientid(String v2clientid) {
           V2clientid = v2clientid;
    }

    public String getV2granttype() {
           return V2granttype;
    }

    public void setV2granttype(String v2granttype) {
           V2granttype = v2granttype;
    }

    public String getV2scope() {
           return V2scope;
    }

    public void setV2scope(String v2scope) {
           V2scope = v2scope;
    }

    public String getV2username() {
           return V2username;
    }

    public void setV2username(String v2username) {
           V2username = v2username;
    }

    public String getV2password() {
           return V2password;
    }

    public void setV2password(String v2password) {
           V2password = v2password;
    }

    public String getV2TPSUSERGroupURL() {
           return V2TPSUSERGroupURL;
    }

    public void setV2TPSUSERGroupURL(String v2tpsuserGroupURL) {
           V2TPSUSERGroupURL = v2tpsuserGroupURL;
    }

    public String getV2groupName() {
           return V2groupName;
    }

    public void setV2groupName(String v2groupName) {
           V2groupName = v2groupName;
    }

    public String getV2roleName() {
           return V2roleName;
    }

    public void setV2roleName(String v2roleName) {
           V2roleName = v2roleName;
    }

	public String getUsermanagementUrl() {
		return usermanagementUrl;
	}

	public void setUsermanagementUrl(String usermanagementUrl) {
		this.usermanagementUrl = usermanagementUrl;
	}
    



}
