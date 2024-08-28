package org.gs.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.gs.MicroProfileRestClient.TvSerieProxy;

@Readiness
public class ReadinessCheck implements HealthCheck {

    @RestClient
    TvSerieProxy proxy;

    @Override
    public HealthCheckResponse call() {
        try {
            proxy.get("hello");
            return HealthCheckResponse.up("Service is ready, external API is accessible");
        } catch (Exception e) {
            return HealthCheckResponse.down("Service is not ready, external API is inaccessible");
        }
    }
}

