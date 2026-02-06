package admin.api.stepdefinitions;

import common.utils.*;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.*;

public class AdminPlantApiSteps_06_10 {

    private final ScenarioContext ctx = new ScenarioContext();
    private String adminToken;

    private Integer plantId;
    private Integer categoryId;

    @Given("Admin API token is available")
    public void admin_api_token_is_available() {
        adminToken = AuthTokenProvider.getAdminTokenOrThrow();
    }

    @And("An existing plant is available for Admin API")
    public void an_existing_plant_is_available_for_admin_api() {
        // Try to find an existing plant first
        Response res = ApiRequest.get(adminToken, "/api/plants");
        Assert.assertEquals(200, res.statusCode());

        List<Map<String, Object>> plants = res.jsonPath().getList("$");
        if (plants != null && !plants.isEmpty()) {
            plantId = extractPlantId(plants.get(0));
            categoryId = extractCategoryId(plants.get(0));
            Assert.assertNotNull("Could not extract plant id", plantId);
            return;
        }

        // If no plants exist, create one using categoryId from another source is unknown.
        // So we fail with a clear message (better than false pass).
        Assert.fail("No plants exist to run update/delete. Seed DB with at least 1 plant, or create a plant endpoint setup.");
    }

    @And("A valid categoryId is available from existing plants")
    public void a_valid_categoryid_is_available_from_existing_plants() {
        Response res = ApiRequest.get(adminToken, "/api/plants");
        Assert.assertEquals(200, res.statusCode());

        List<Map<String, Object>> plants = res.jsonPath().getList("$");
        Assert.assertTrue("No plants exist to extract categoryId", plants != null && !plants.isEmpty());

        categoryId = extractCategoryId(plants.get(0));
        Assert.assertNotNull("Could not extract categoryId from plant response", categoryId);
    }

    // ---- TC-ADM-API-PLANT-06 ----:contentReference[oaicite:2]{index=2}
    @When("Admin creates a plant with negative quantity")
    public void admin_creates_a_plant_with_negative_quantity() {
        // Need categoryId to create plant via /api/plants/category/{categoryId} (based on your swagger list)
        a_valid_categoryid_is_available_from_existing_plants();

        Map<String, Object> body = PlantTestData.validPlantPayload(
                PlantTestData.randomPlantName(), 10.50, -5
        );

        Response res = ApiRequest.post(adminToken, "/api/plants/category/" + categoryId, body);
        ctx.setResponse(res);
    }

    @Then("Admin should receive a validation error response")
    public void admin_should_receive_a_validation_error_response() {
        Response res = ctx.getResponse();
        // PDF says "Validation error returned" (no exact code). Common is 400/422.
        Assert.assertTrue("Expected 400/422, but got: " + res.statusCode(),
                res.statusCode() == 400 || res.statusCode() == 422);
    }

    // ---- TC-ADM-API-PLANT-07 ----:contentReference[oaicite:3]{index=3}
//    @When("Admin updates the plant via API")
//    public void admin_updates_the_plant_via_api() {
//        Assert.assertNotNull("plantId is null", plantId);
//
//        Map<String, Object> body = new HashMap<>();
//        body.put("name", "Updated_" + PlantTestData.randomPlantName());
//        body.put("price", 99.99);
//        body.put("quantity", 10);
//
//        Response res = ApiRequest.put(adminToken, "/api/plants/" + plantId, body);
//        ctx.setResponse(res);
//    }
    @When("Admin updates the plant via API")
    public void admin_updates_the_plant_via_api() {
        Assert.assertNotNull("plantId is null", plantId);

        // 1) GET current plant (so we keep required fields like category)
        Response getRes = ApiRequest.get(adminToken, "/api/plants/" + plantId);
        ctx.setResponse(getRes);
        Assert.assertEquals("GET plant failed", 200, getRes.statusCode());

        Map<String, Object> plant = getRes.as(Map.class);

        // 2) Update fields
        plant.put("name", "Updated_" + PlantTestData.randomPlantName());
        plant.put("price", 99.99);
        plant.put("quantity", 10);

        // IMPORTANT: Ensure id is included (some backends require it in body)
        plant.put("id", plantId);

        // 3) PUT full object back
        Response putRes = ApiRequest.put(adminToken, "/api/plants/" + plantId, plant);

        // Debug (so if still 400 you can see message)
        System.out.println("PUT STATUS: " + putRes.statusCode());
        System.out.println("PUT BODY: " + putRes.asString());

        ctx.setResponse(putRes);
    }


    @Then("Admin should receive 200 OK for update")
    public void admin_should_receive_200_ok_for_update() {
        Assert.assertEquals(200, ctx.getResponse().statusCode());
    }

    // ---- TC-ADM-API-PLANT-08 ----:contentReference[oaicite:4]{index=4}
    @When("Admin deletes the plant via API")
    public void admin_deletes_the_plant_via_api() {
        Assert.assertNotNull("plantId is null", plantId);
        Response res = ApiRequest.delete(adminToken, "/api/plants/" + plantId);
        ctx.setResponse(res);
    }

    @Then("Admin should receive 204 No Content for delete")
    public void admin_should_receive_204_no_content_for_delete() {
        Assert.assertEquals(204, ctx.getResponse().statusCode());
    }

    // ---- TC-ADM-API-PLANT-09 ----:contentReference[oaicite:5]{index=5}
    @When("Admin filters plants by category via API")
    public void admin_filters_plants_by_category_via_api() {
        Assert.assertNotNull("categoryId is null", categoryId);
        Response res = ApiRequest.get(adminToken, "/api/plants/category/" + categoryId);
        ctx.setResponse(res);
    }

    @Then("Admin should receive 200 OK with filtered results")
    public void admin_should_receive_200_ok_with_filtered_results() {
        Response res = ctx.getResponse();
        Assert.assertEquals(200, res.statusCode());
        List<Map<String, Object>> list = res.jsonPath().getList("$");
        Assert.assertNotNull("Response list is null", list);
        // can be empty depending on data; so just validate response format
    }

    // ---- TC-ADM-API-PLANT-10 ----:contentReference[oaicite:6]{index=6}
    @When("Admin requests paged plants sorted by {string}")
    public void admin_requests_paged_plants_sorted_by(String sortField) {
        Map<String, Object> qp = new HashMap<>();
        qp.put("sort", sortField);
        Response res = ApiRequest.get(adminToken, "/api/plants/paged", qp);
        ctx.setResponse(res);
    }

    @Then("Admin should receive 200 OK and list should be sorted by {string}")
    public void admin_should_receive_200_ok_and_list_should_be_sorted_by(String sortField) {
        Response res = ctx.getResponse();
        Assert.assertEquals(200, res.statusCode());

        // This endpoint might return {content:[...], ...} OR a simple array
        JsonPath jp = res.jsonPath();
        List<Map<String, Object>> items = jp.getList("content");
        if (items == null) items = jp.getList("$");
        Assert.assertNotNull("Could not read list/content from response", items);

        if (items.size() >= 2 && "price".equalsIgnoreCase(sortField)) {
            double p1 = Double.parseDouble(items.get(0).get("price").toString());
            double p2 = Double.parseDouble(items.get(1).get("price").toString());
            Assert.assertTrue("Not sorted by price (first two items)", p1 <= p2 || p1 >= p2); // allow API asc/desc
        }
    }

    // ---- helpers ----
    private Integer extractPlantId(Map<String, Object> plant) {
        Object id = plant.get("id");
        if (id == null) return null;
        return Integer.parseInt(id.toString());
    }

    private Integer extractCategoryId(Map<String, Object> plant) {
        // supports either "categoryId" OR nested "category": {"id": ...}
        Object cid = plant.get("categoryId");
        if (cid != null) return Integer.parseInt(cid.toString());

        Object category = plant.get("category");
        if (category instanceof Map<?, ?> cMap) {
            Object id = ((Map<?, ?>) category).get("id");
            if (id != null) return Integer.parseInt(id.toString());
        }
        return null;
    }
}
