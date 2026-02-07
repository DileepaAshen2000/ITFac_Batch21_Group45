Feature: fetch sales as admin

@Admin
@TC_ADM_API_SALES_11
Scenario: Verify Admin can fetch Sales list
    Given Admin has a valid token
    When Admin sends GET request to "/api/sales"
    Then response status should be 200
    And response should contain sales list