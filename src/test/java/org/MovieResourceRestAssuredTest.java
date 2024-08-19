package org;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import io.quarkus.test.junit.QuarkusTest;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieResourceRestAssuredTest {
    @Test
    @Order(1)
    void getAll(){
        given()
            .when()
            .get("/movie")
            .then()
            .body("title", hasItems("Parasite", "Inception"))
            .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    @Order(3)
    void create() {
      JsonObject jsonObject =
          Json.createObjectBuilder()
              .add("title", "ThirdMovie")
              .add("description", "MyThirdMovie")
              .add("director", "Me")
              .add("country", "Planet")
              .build();
  
      given()
          .contentType(MediaType.APPLICATION_JSON)
          .body(jsonObject.toString())
          .when()
          .post("/movie")
          .then()
          .statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Test
    @Order(3)
    void deleteById() {
      given()
          .when()
          .delete("/movie/2")
          .then()
          .statusCode(Response.Status.NO_CONTENT.getStatusCode());
  
      given().when().get("/movie/2").then().statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}
