Feature: User can not delete sales

@User
@TC_USR_API_SALES_13
  Scenario: Verify User can't delete sales
    Given User has a valid token
    And A sale exists with id "{saleId}"
    When User sends DELETE request to "/api/sales/{saleId}"
    Then response status should be 403