Feature: Admin can not create sell plant with invalid quantity

@Admin
@TC_ADM_API_SALES_14
Scenario: Verify Create sale invalid quantity
    Given Admin has a valid token
    And A valid plantId exists
    When Admin sends POST request to "/api/sales/plant/{plantId}" with quantity 0
    Then response status should be 400
    And response should contain invalid quantity message

@Admin
@TC_ADM_API_SALES_14
Scenario: Verify Create sale invalid quantity
    Given Admin has a valid token
    And A valid plantId exists
    When Admin sends POST request to "/api/sales/plant/{plantId}" with quantity 0
    Then response status should be 400
    And response should contain invalid quantity message