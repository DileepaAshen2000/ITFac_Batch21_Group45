Feature: stock is restored when sale deleted

@Admin
@TC_ADM_API_SALES_18
  Scenario: Verify stock is restored when sale is deleted
    Given Admin has a valid token
    And A sale exists with id "{saleId}" for plant "{plantId}" with quantity 1
    When Admin sends DELETE request to "/api/sales/{saleId}"
    Then response status should be 204
    And Plant stock should be restored by 1 for "{plantId}"