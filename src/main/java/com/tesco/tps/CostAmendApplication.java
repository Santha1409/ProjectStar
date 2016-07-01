package com.tesco.tps;

import java.io.IOException;
import java.net.URISyntaxException;

import com.tesco.tps.cdi.bundle.TradingPartnerCdiBundle;
import com.tesco.tps.cdi.config.TradingPartnerCdiConfig;
import com.tesco.tps.configuration.CostAmendConfiguration;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * Dropwizard main application where HealthCheck, Resources would be registered.
 * 
 * 
 * @author a483 Rabindra 27 Aug 2015
 * 
 */
public class CostAmendApplication extends Application<CostAmendConfiguration> {

	public static CostAmendConfiguration costAmendConfiguration=null;
	public static void main(String[] args) throws Exception {
		new CostAmendApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<CostAmendConfiguration> bootstrap) {
		bootstrap.addBundle(new SwaggerBundle<CostAmendConfiguration>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(CostAmendConfiguration configuration) {
				return configuration.swaggerBundleConfiguration;
			}
		});
		bootstrap.addBundle(new TradingPartnerCdiBundle<CostAmendConfiguration>() {
			@Override
			protected TradingPartnerCdiConfig getTradingPartnerCoreConfig(CostAmendConfiguration config) {
				return config.getTpsConfig();
			}

		});
	}

	@Override
	public String getName() {
		return "TradingPartner-Services-Application";
	}

	@Override
	public void run(CostAmendConfiguration configuration, Environment environment) throws URISyntaxException,
			IOException, InterruptedException {
		costAmendConfiguration=configuration;
		configureMetrics(configuration);
	}

	private void configureMetrics(CostAmendConfiguration configuration) {

	}

}
