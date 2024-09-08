package org.example.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.restassured.AllureRestAssured;

public class ApiConfig {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";

    public static RequestSpecification getRequestPetStore() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Content-Type", "application/json")
                .addFilter(new AllureRestAssured()) // Integra o Allure para registrar as requisições
                .log(LogDetail.ALL) // Adiciona logging detalhado para melhor depuração
                .build();
    }
}
