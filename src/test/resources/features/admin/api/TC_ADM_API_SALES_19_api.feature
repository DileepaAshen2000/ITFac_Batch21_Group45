Feature: Admin can get sales by Id

@Admin
@TC_ADM_API_SALES_19
  Scenario: Verify Admin can get Sale by ID
    Given Admin has a valid token
    And A sale exists with id "{saleId}"
    When Admin sends GET request to "/api/sales/{saleId}"
    Then response status should be 200
    And response should contain sale details for "{saleId}"