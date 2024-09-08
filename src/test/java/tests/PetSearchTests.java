package tests;

import io.restassured.response.Response;
import org.example.service.PetSearchService;
import org.example.service.PetStatusSearchService;
import org.example.models.PetSearchModel;
import org.example.models.PetStatusSearchModel;
import data.TestDataProvider;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Pet Store")
@Feature("Pet Search")
public class PetSearchTests {
    private static final Logger logger = LoggerFactory.getLogger(PetSearchTests.class);
    private PetSearchService petSearchService;
    private PetStatusSearchService petStatusSearchService;

    @BeforeEach
    public void setup() {
        petSearchService = new PetSearchService();
        petStatusSearchService = new PetStatusSearchService();
    }

    @Test
    @Story("Busca por pet inexistente")
    @Description("Valida que a busca por um pet inexistente retorna o status 404")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchNonExistentPet() {
        Allure.step("Busca por um pet inexistente usando o ID fornecido pelo TestDataProvider");
        Response response = petSearchService.searchPet(TestDataProvider.NON_EXISTENT_PET_ID);
        logger.info("Response Body: {}", response.getBody().asString());

        Allure.step("Validando que o código de status é 404 (Pet não encontrado)");
        assertThat(response.getStatusCode()).isEqualTo(404);

        Allure.step("Validando a estrutura da resposta conforme o Swagger");
        PetSearchModel searchResult = response.as(PetSearchModel.class);
        assertThat(searchResult.code()).isEqualTo("1"); // Código de erro esperado
        assertThat(searchResult.type()).isEqualTo("error"); // Tipo de resposta esperado
        assertThat(searchResult.message()).isEqualTo("Pet not found"); // Mensagem de erro esperada
    }

    @Test
    @Story("Busca por pets com status pendente")
    @Description("Valida que a busca por pets com status 'pending' retorna resultados corretos")
    @Severity(SeverityLevel.NORMAL)
    public void testSearchPendingPetStatus() {
        Allure.step("Busca por pets com status 'pending'");
        Response response = petStatusSearchService.searchPetByStatus(TestDataProvider.PENDING_STATUS);
        logger.info("Response Body: {}", response.getBody().asString());

        Allure.step("Validando que o código de status é 200 (Operação bem-sucedida)");
        assertThat(response.getStatusCode()).isEqualTo(200);

        Allure.step("Validando que todos os pets retornados têm o status 'pending'");
        PetStatusSearchModel[] pets = response.as(PetStatusSearchModel[].class);
        assertThat(pets).isNotEmpty(); // Verifica se há pelo menos 1 pet na lista
        assertThat(pets).allMatch(pet -> TestDataProvider.PENDING_STATUS.equals(pet.status()));
    }
}
