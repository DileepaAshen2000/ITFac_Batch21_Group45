@TC_ADM_UI_CAT_10
Feature: Admin Category Management - Success Message Persistence

  Scenario: Verify success message persistence after adding category
    Given Admin is logged into the system
    And Admin navigates to "/ui/categories/add"
    When Admin adds a new category with name "TestCat"
    Then the success message "Category created successfully" should be displayed
    And the success message should remain visible after 5 seconds
    When Admin clicks Reset
    Then the success message should disappear