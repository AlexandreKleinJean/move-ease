package org.gs.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.gs.MicroProfileRestClient.TvSerieProxy;

@Liveness
public class LivenessCheck implements HealthCheck {

    @RestClient
    TvSerieProxy proxy;

    @Override
    public HealthCheckResponse call() {
        proxy.get("hello");
        return HealthCheckResponse.up("Service is aliiiiiive");
    }
}

