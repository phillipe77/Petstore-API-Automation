package org.example.service;

import io.restassured.response.Response;
import org.example.config.ApiConfig;

import static io.restassured.RestAssured.given;

public class PetSearchService {
    public Response searchPet(long petId) {
        return given()
                .spec(ApiConfig.getRequestPetStore())
                .when()
                .get("/pet/" + petId);
    }
}
