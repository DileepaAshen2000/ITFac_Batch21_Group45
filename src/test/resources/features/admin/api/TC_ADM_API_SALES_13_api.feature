Feature: Admin can create sell plant

@Admin
@TC_ADM_API_SALES_13
Scenario: Verify Admin can create Sell Plant via API
    Given Admin has a valid token
    And A valid plantId exists with sufficient stock
    When Admin sends POST request to "/api/sales/plant/{plantId}" with quantity 1
    Then response status should be 201
    And Sale should be created successfully