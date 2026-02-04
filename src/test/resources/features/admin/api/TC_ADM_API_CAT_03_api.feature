Feature: Create sub category as Admin

  @Admin
  @TC_ADM_API_CAT_03
  Scenario: Verify create sub category API
    Given Admin is authenticated
    When Admin sends POST request to create sub category
    Then response status should be 201
    And response should contain created sub category details
