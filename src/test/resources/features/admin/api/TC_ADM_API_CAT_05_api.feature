@Admin @TC_ADM_API_CAT_05
Feature: Update Category API

  Scenario: Verify update category API
    Given Admin is authenticated
    And Admin has an existing category to update
    When Admin sends PUT request to update the category
    Then response status should be 200
    And response should contain updated category details
    And updated category should reflect in category list
