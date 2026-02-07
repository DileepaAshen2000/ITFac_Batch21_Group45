@API @CategoryManagement
Feature: Category API Management Validation

  Background:
    Given Admin is logged into the system
    And Admin is authenticated with the API session

  @TC_ADM_API_CAT_01
  Scenario: Verify API rejects category creation with name shorter than 3 characters
    When Admin sends a POST request to "/api/categories" with name "AB"
    Then the API response status should be 400
    And the response body should contain the error "Category name must be between 3 and 10 characters"

  @TC_ADM_API_CAT_02
  Scenario: Verify API rejects category creation with name longer than 10 characters
    When Admin sends a POST request to "/api/categories" with name "ABCDEFGHIJKL"
    Then the API response status should be 400
    And the response body should contain the error "Category name must be between 3 and 10 characters"

  @TC_ADM_API_CAT_03
  Scenario: Verify failure for duplicate main category
    Given a category with name "ABC" already exists
    When Admin sends a POST request to "/api/categories" with name "ABC"
    Then the API response status should be 400 or 409
    And the response body should contain "already exists"

  @TC_ADM_API_CAT_04
  Scenario: Verify failure for duplicate sub-category under parent
    Given a parent category exists with ID 10
    And a sub-category with name "ABC1" already exists under parent 10
    When Admin sends a POST request to "/api/categories" with name "ABC1" and parentId 10
    Then the API response status should be 400 or 409
    And the response body should contain "already exists under this parent"

  @TC_ADM_API_CAT_05
  Scenario: Verify fetch categories with search and filter
    When Admin sends a GET request to "/api/categories" with params:
      | search | DEF |
      | parent | ABC |
    Then the API response status should be 200
    And the response should return a filtered list