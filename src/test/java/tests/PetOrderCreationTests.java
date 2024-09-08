package tests;

import io.restassured.response.Response;
import org.example.service.PetOrderService;
import org.example.models.PetOrderModel;
import data.TestDataProvider;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Pet Store")
@Feature("Pet Order Creation")
public class PetOrderCreationTests {
    private static final Logger logger = LoggerFactory.getLogger(PetOrderCreationTests.class);
    private PetOrderService petOrderService;

    @BeforeEach
    public void setup() {
        petOrderService = new PetOrderService();
    }

    @Test
    @Story("Criar um novo pedido de pet")
    @Description("Valida que a criação de um novo pedido de pet retorna sucesso e que os dados são corretos.")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateNewPetOrder() {
        // Criando um novo pedido de pet usando o TestDataProvider que já retorna um record
        PetOrderModel newOrder = TestDataProvider.createNewPetOrder();
        logger.info("Generated order data: {}", newOrder);
        logOrderDetails(newOrder);

        Allure.step("Chamada para a API para criar o pedido");
        Response response = petOrderService.createPetOrder(newOrder);
        logger.info("API Response: Status Code = {}, Body = {}", response.getStatusCode(), response.getBody().asString());

        Allure.step("Validando que a resposta retorna um código de status 200");
        assertThat(response.getStatusCode()).isEqualTo(200);

        // Convertendo a resposta para um modelo de PetOrder
        PetOrderModel responseOrder = response.as(PetOrderModel.class);

        Allure.step("Validando os campos da resposta conforme o pedido original");
        validateOrderFields(newOrder, responseOrder);
    }

    @Step("Log dos detalhes do pedido")
    private void logOrderDetails(PetOrderModel order) {
        logger.info("Order ID: {}", order.id());
        logger.info("Pet ID: {}", order.petId());
        logger.info("Quantity: {}", order.quantity());
        logger.info("Status: {}", order.status());
        logger.info("Complete: {}", order.complete());
        logger.info("Ship Date: {}", order.shipDate());
    }

    @Step("Validando os campos do pedido")
    private void validateOrderFields(PetOrderModel newOrder, PetOrderModel responseOrder) {
        assertThat(responseOrder.id()).isEqualTo(newOrder.id());
        assertThat(responseOrder.petId()).isEqualTo(newOrder.petId());
        assertThat(responseOrder.quantity()).isEqualTo(newOrder.quantity());
        assertThat(responseOrder.status()).isEqualTo(newOrder.status());
        assertThat(responseOrder.complete()).isEqualTo(newOrder.complete());

        // Verificando que a data de envio não é nula e está em formato ISO-8601
        assertThat(responseOrder.shipDate()).isNotNull();
        assertThat(responseOrder.shipDate()).matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{3})?(Z|[+-]\\d{2}:?\\d{2})?");
    }
}
