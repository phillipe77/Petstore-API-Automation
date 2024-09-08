package org.example.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.config.ApiConfig;
import org.example.models.PetUpdateModel;

import static io.restassured.RestAssured.given;

public class PetUpdateService {

    public Response updatePet(PetUpdateModel petUpdate) {

        return given()
                .spec(ApiConfig.getRequestPetStore())
                .accept("application/json")
                .contentType("application/json")
                .body(petUpdate)
                .when()
                .put("/pet");
    }
}
