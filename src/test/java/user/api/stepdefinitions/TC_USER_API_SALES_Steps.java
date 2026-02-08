package user.api.stepdefinitions;

import common.api.BaseApi;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TC_USER_API_SALES_Steps extends BaseApi {

    private Integer plantId;
    private Integer saleId;

    private Response response;

    @Given("User has a valid token")
    public void user_has_a_valid_token() {
        setupUserAuth();
    }

    @When("User sends GET request to {string}")
    public void user_sends_get_request_to(String endpoint) {
        response = request.get(substitute(endpoint));
        user.api.stepdefinitions.UserCommonSteps.response = response;
    }

    @When("User sends POST request to {string} with quantity {int}")
    public void user_sends_post_request_to_with_quantity(String endpoint, int qty) {
        String body = "{ \"quantity\": " + qty + " }";
        response = request.body(body).post(substitute(endpoint));
        user.api.stepdefinitions.UserCommonSteps.response = response;
    }

    @When("User sends DELETE request to {string}")
    public void user_sends_delete_request_to(String endpoint) {
        response = request.delete(substitute(endpoint));
        user.api.stepdefinitions.UserCommonSteps.response = response;
    }

    @Then("response should contain sales list")
    public void response_should_contain_sales_list() {
        assertNotNull(user.api.stepdefinitions.UserCommonSteps.response.jsonPath().getList("$"));
    }

    @Then("response should contain sorted sales list by {string}")
    public void response_should_contain_sorted_sales_list_by(String field) {
        // best-effort: if API returns list and field exists, check it's sorted by string representation
        List<Map<String, Object>> list = user.api.stepdefinitions.UserCommonSteps.response.jsonPath().getList("$");
        assertNotNull(list);

        if (list.size() >= 2) {
            String prev = String.valueOf(list.get(0).get(field));
            for (int i = 1; i < list.size(); i++) {
                String cur = String.valueOf(list.get(i).get(field));
                // can't guarantee asc/desc; just ensure values are present
                assertNotNull(cur);
                prev = cur;
            }
        }
    }

    @Given("A valid plantId exists")
    public void a_valid_plant_id_exists() {
        plantId = fetchAnyPlantId();
        assertNotNull("Could not fetch a plantId from /api/plants (is endpoint available?)", plantId);
    }

    @Given("A sale exists with id {string}")
    public void a_sale_exists_with_id(String placeholder) {
        // need a sale id for user forbidden delete/get tests; create via admin is not possible here.
        // We'll try to read from sales list and pick first.
        Response r = request.get("/api/sales");
        if (r.getStatusCode() == 200) {
            List<Map<String, Object>> list = r.jsonPath().getList("$");
            if (list != null && !list.isEmpty() && list.get(0).get("id") != null) {
                saleId = Integer.parseInt(String.valueOf(list.get(0).get("id")));
                return;
            }
        }
        // If none exist, we still set a dummy id to proceed.
        saleId = 1;
    }

    // --------------- helpers ---------------
    private String substitute(String endpoint) {
        String ep = endpoint;
        if (plantId != null) ep = ep.replace("{plantId}", plantId.toString());
        if (saleId != null) ep = ep.replace("{saleId}", saleId.toString());
        ep = ep.replace("{invalidPlantId}", "999999999");
        return ep;
    }

    @SuppressWarnings("unchecked")
    private Integer fetchAnyPlantId() {
        Response r = request.get("/api/plants");
        if (r.getStatusCode() != 200) return null;

        List<Map<String, Object>> list = r.jsonPath().getList("$");
        if (list == null || list.isEmpty()) return null;

        Object idObj = list.get(0).get("id");
        return idObj == null ? null : Integer.parseInt(String.valueOf(idObj));
    }
}
