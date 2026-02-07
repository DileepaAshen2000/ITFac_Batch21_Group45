Feature: Admin can delete sales

@Admin
@TC_ADM_API_SALES_16
  Scenario: Verify Admin can delete sales via API
    Given Admin has a valid token
    And A sale exists with id "{saleId}"
    When Admin sends DELETE request to "/api/sales/{saleId}"
    Then response status should be 204