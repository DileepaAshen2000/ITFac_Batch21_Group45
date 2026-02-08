Feature: Create main category as Admin

  @Admin
  @TC_ADM_API_CAT_02
  Scenario: Verify create main category API
    Given Admin is authenticated
    When Admin sends POST request to create main category
    Then response status should be 201
    And response should contain created category details
