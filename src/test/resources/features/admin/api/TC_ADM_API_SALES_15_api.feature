Feature: Admin can not create sell plant with invalid plant

@Admin
@TC_ADM_API_SALES_15
  Scenario: Verify Create sale invalid Plant
    Given Admin has a valid token
    When Admin sends POST request to "/api/sales/plant/{invalidPlantId}" with quantity 1
    Then response status should be 404
    And response should contain plant not found message