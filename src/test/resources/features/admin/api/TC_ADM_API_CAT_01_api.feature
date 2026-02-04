Feature: Fetch categories as Admin

  @Admin
  @TC_ADM_API_CAT_01
  Scenario: Verify fetch categories API as Admin
    Given Admin is authenticated
    When Admin sends GET request to fetch categories
    Then response status should be 200
    And response should contain category list
