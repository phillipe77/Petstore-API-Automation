package data;

import org.example.models.PetOrderModel;
import org.example.models.PetUpdateModel;

import java.time.Instant;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

public class TestDataProvider {
    private static final Random random = new Random();

    // Método para criar um novo pedido de pet conforme a documentação Swagger
    public static PetOrderModel createNewPetOrder() {
        return new PetOrderModel(
                generateRandomId(),
                generateRandomId(),
                generateRandomQuantity(),
                Instant.now().toString(), // Assumindo que o formato de data aceito é ISO-8601
                generateRandomOrderStatus(), // Status do pedido de pet
                random.nextBoolean()
        );
    }

    // Método para criar dados de atualização de pet conforme a documentação Swagger
    public static PetUpdateModel createPetUpdate() {
        return new PetUpdateModel(
                generateRandomId(),
                generateRandomPetName(),
                generateRandomPetStatus(), // Status permitido para atualização de pet
                Collections.singletonList("https://example.com/" + UUID.randomUUID() + ".jpg")
        );
    }

    private static long generateRandomId() {
        return Math.abs(random.nextLong()) % 10000000;
    }

    private static int generateRandomQuantity() {
        return random.nextInt(5) + 1; // Quantidade de 1 a 5
    }

    // Status permitidos para pedidos de pet
    private static String generateRandomOrderStatus() {
        String[] orderStatuses = {"placed", "approved", "delivered"};
        return orderStatuses[random.nextInt(orderStatuses.length)];
    }

    // Status permitidos para atualização de pet
    private static String generateRandomPetStatus() {
        String[] petStatuses = {"available", "pending", "sold"};
        return petStatuses[random.nextInt(petStatuses.length)];
    }

    private static String generateRandomPetName() {
        String[] names = {"Zed", "Irelia", "Jinx", "Azir", "Shen", "Zyra", "Corki", "Sona"};
        return names[random.nextInt(names.length)];
    }

    public static final long NON_EXISTENT_PET_ID = 99999999; // ID de pet inexistente para testes
    public static final String PENDING_STATUS = "pending"; // Status pendente usado em pesquisas
}
