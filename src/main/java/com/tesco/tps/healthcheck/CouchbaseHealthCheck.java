package com.tesco.tps.healthcheck;

import static org.slf4j.LoggerFactory.getLogger;

import java.net.SocketAddress;
import java.util.Map;

import org.slf4j.Logger;

import com.codahale.metrics.health.HealthCheck;
import com.tesco.tps.couchbase.AsyncCouchbaseWrapper;
import com.tesco.tps.couchbase.exceptions.CouchbaseConnectionUnavailableException;

public class CouchbaseHealthCheck extends HealthCheck {
	
    private static final Logger LOG = getLogger(CouchbaseHealthCheck.class);

    public static final String NAME = "Couchbase Health Check";
    public static final String EP_DEGRADED_MODE = "ep_degraded_mode";
    private final AsyncCouchbaseWrapper asyncCouchbaseWrapper;

    public CouchbaseHealthCheck(AsyncCouchbaseWrapper asyncCouchbaseWrapper) {
        super();
        this.asyncCouchbaseWrapper = asyncCouchbaseWrapper;
    }

    @Override
    protected Result check() throws Exception {

    	LOG.info("<< CouchbaseHealthCheck :: check() >>");
        if(couchbaseReady()){
            return Result.healthy();
        }

        return Result.unhealthy("CouchbaseServer is not ready.");
    }

    private boolean couchbaseReady(){
        try {
        	/** Get all of the stats from all of the connections. **/
            Map<SocketAddress, Map<String, String>> stats = asyncCouchbaseWrapper.getStats();
            for (Map.Entry<SocketAddress, Map<String, String>> server: stats.entrySet()) {
                Map<String, String> serverStats = server.getValue();
                if (!statsAvailable(serverStats) || !serverWarmedUp(serverStats)){
                    return false;
                }
            }
            return true;
        } catch (CouchbaseConnectionUnavailableException e) {
            if(LOG.isErrorEnabled()) {
                LOG.error("Connection lost due to : " + e.getMessage());
            }
            return false;
        }
    }

    private boolean serverWarmedUp(Map<String, String> serverStats) {
    	/** True if the engine is either warming up or data traffic is disabled **/
        return ("0").equals(serverStats.get(EP_DEGRADED_MODE));
    }

    private boolean statsAvailable(Map<String, String> serverStats) {
        return serverStats.containsKey(EP_DEGRADED_MODE);
    }
}
