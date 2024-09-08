package org.example.service;

import io.restassured.response.Response;
import org.example.config.ApiConfig;

import static io.restassured.RestAssured.given;

public class PetStatusSearchService {

    public Response searchPetByStatus(String status) {
        return given()
                .spec(ApiConfig.getRequestPetStore())
                .accept("application/json")
                .when()
                .get("/pet/findByStatus?status=" + status);
    }
}
