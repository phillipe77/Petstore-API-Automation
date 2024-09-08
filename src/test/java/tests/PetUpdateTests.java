package tests;

import io.restassured.response.Response;
import org.example.models.PetUpdateModel;
import org.example.service.PetUpdateService;
import data.TestDataProvider;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Pet Store")
@Feature("Pet Update")
public class PetUpdateTests {
    private static final Logger logger = LoggerFactory.getLogger(PetUpdateTests.class);
    private PetUpdateService petUpdateService;

    @BeforeEach
    public void setup() {
        petUpdateService = new PetUpdateService();
    }

    @Test
    @Story("Atualizar informações de um pet")
    @Description("Valida que a atualização das informações de um pet é realizada corretamente via API.")
    @Severity(SeverityLevel.CRITICAL)
    public void testUpdatePetInformation() {
        Allure.step("Gerando dados de atualização de pet usando o TestDataProvider");
        PetUpdateModel petUpdateModel = TestDataProvider.createPetUpdate();
        logger.info("Generated pet update data: {}", petUpdateModel);

        Allure.step("Chamada para a API de atualização de pet");
        Response response = petUpdateService.updatePet(petUpdateModel);
        logger.info("API Response: Status Code = {}, Body = {}", response.getStatusCode(), response.getBody().asString());

        Allure.step("Validando que o código de status retornado é 200");
        if (response.getStatusCode() != 200) {
            logger.error("Failed to update pet. Status Code: {}, Body: {}", response.getStatusCode(), response.getBody().asString());
            assertThat(response.getStatusCode())
                    .withFailMessage("Expected status code 200 but got " + response.getStatusCode())
                    .isEqualTo(200);
        }

        Allure.step("Validando os dados retornados da API para garantir que o update foi aplicado corretamente");
        validateUpdatedPet(petUpdateModel, response);
    }

    @Step("Validando os dados atualizados do pet")
    private void validateUpdatedPet(PetUpdateModel petUpdateModel, Response response) {
        assertThat(response.jsonPath().getLong("id")).isEqualTo(petUpdateModel.id());
        assertThat(response.jsonPath().getString("name")).isEqualTo(petUpdateModel.name());
        assertThat(response.jsonPath().getString("photoUrls[0]")).isEqualTo(petUpdateModel.photoUrls().get(0));
        assertThat(response.jsonPath().getString("status")).isEqualTo(petUpdateModel.status());
    }
}
