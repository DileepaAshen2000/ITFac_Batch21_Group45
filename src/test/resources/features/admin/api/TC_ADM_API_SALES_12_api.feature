Feature: Verify pagination

@Admin
@TC_ADM_API_SALES_12
Scenario: Verify pagination in Sales list via API
    Given Admin has a valid token
    And Sales exceed page size
    When Admin sends GET request to "/api/sales/page"
    Then response status should be 200
    And response should contain paginated sales data