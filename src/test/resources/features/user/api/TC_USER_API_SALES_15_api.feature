Feature: User can get sale by Id

@User
@TC_USR_API_SALES_15
  Scenario: Verify User can get Sale by ID
    Given User has a valid token
    And A sale exists with id "{saleId}"
    When User sends GET request to "/api/sales/{saleId}"
    Then response status should be 200
    And response should contain sale details for "{saleId}"