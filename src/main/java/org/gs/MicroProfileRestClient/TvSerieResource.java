package org.gs.MicroProfileRestClient;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tvseries")
public class TvSerieResource {
    
    @RestClient
    TvSerieProxy proxy;

    public List<TvSerie> tvSeries = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name= "timeFetchTvSeries", description = "Count of the fetch")
    @Timed(name= "fetchDuration", description = "Duration of the fetch", unit = MetricUnits.MILLISECONDS)
    @Metered(name= "fetchRate", description = "Rate of the fetch")
    public Response get(){
        TvSerie tvSerie = proxy.get("game of thrones");
        tvSeries.add(tvSerie);
        return Response.ok(tvSeries).build();
    }
}
