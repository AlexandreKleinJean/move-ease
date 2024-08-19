package org;

import io.quarkus.test.common.WithTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@WithTestResource(TvSerieAPIWiremock.class)
public class TvSerieResourceWiremockTest {

  @Test
  void getWithWiremock() {
    given()
        .when()
        .param("title", "myTvSerie")
        .get("/tvseries")
        .then()
        .body("id", equalTo(1))
        .body("name", equalTo("myTvSerie"))
        .body("url", equalTo("https://www.tvmaze.com/shows/2993/my-tv-series"))
        .body("language", equalTo("English"))
        .body("officialSite", equalTo("https://www.netflix.com/title/80057281"))
        .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode());
  }
}
