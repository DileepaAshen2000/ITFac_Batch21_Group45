package common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlantTestData {

    public static Map<String, Object> validPlantPayload(String name, double price, int quantity) {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("price", price);
        body.put("quantity", quantity);
        return body;
    }

    public static String randomPlantName() {
        return "API_Plant_" + UUID.randomUUID().toString().substring(0, 8);
    }
}
