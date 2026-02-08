@Admin @TC_ADM_API_CAT_04
Feature: Delete Category API

  Scenario: Verify delete category API
    Given Admin is authenticated
    And Admin has an existing category to delete
    When Admin sends DELETE request to delete the category
    Then response status should be 204
    And deleted category should not appear in category list
