package org.example.service;

import io.restassured.response.Response;
import org.example.config.ApiConfig;
import org.example.models.PetOrderModel;

import static io.restassured.RestAssured.given;

public class PetOrderService {

    public Response createPetOrder(PetOrderModel petOrder) {
        return given()
                .spec(ApiConfig.getRequestPetStore())
                .body(petOrder)
                .when()
                .post("/store/order");
    }
}
