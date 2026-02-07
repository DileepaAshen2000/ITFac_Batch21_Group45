@API @TC_ADM_API_CAT_01
Feature: Category API Validation

  Scenario: Verify API rejects category creation with name shorter than 3 characters
    Given Admin is authenticated with the API
    When Admin sends a POST request to "/api/categories" with the following body:
      | name     | AB   |
      | parentId | null |
    Then the API response status should be 400
    And the response body should contain the error "Category name must be between 3 and 10 characters"