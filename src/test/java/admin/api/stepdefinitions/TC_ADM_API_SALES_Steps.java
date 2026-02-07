// package admin.api.stepdefinitions;

// import io.cucumber.java.en.Given;
// import io.cucumber.java.en.Then;
// import io.cucumber.java.en.When;

// import static org.junit.Assert.assertNotNull;

// public class TC_ADM_API_SALES_Steps {

//     @Given("Admin has a valid token")
//     public void admin_has_a_valid_token() {
//         setupAdminAuth(); // this must exist in BaseApi/AdminCommonSteps parent chain
//     }

//     @When("Admin sends GET request to {string}")
//     public void admin_sends_get_request_to(String endpoint) {
//         response = request.get(endpoint);
//     }

//     @Then("response should contain sales list")
//     public void response_should_contain_sales_list() {
//         assertNotNull(response.jsonPath().getList("$"));
//     }
// }