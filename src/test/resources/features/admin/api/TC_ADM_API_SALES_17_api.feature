Feature: Admin can not create sell plant with invalid stock

@Admin
@TC_ADM_API_SALES_17
  Scenario: Verify Create sale with insufficient stock
    Given Admin has a valid token
    And A plant exists with stock 1
    When Admin sends POST request to "/api/sales/plant/{plantId}" with quantity 2
    Then response status should be 400
    And response should contain invalid quantity message