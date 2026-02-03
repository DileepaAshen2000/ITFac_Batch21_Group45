Feature: Admin Category Validation - Maximum Length

  @TC_ADM_UI_CAT_05
  Scenario: Verify validation error for category name longer than 10 characters
    Given Admin is logged into the system
    When Admin navigates to add category page for max length validation
    And Admin enters long category name "ABCDEFGHIJKL"
    And Admin attempts to save category
    Then Length validation error should be displayed
    And Category should not be created
