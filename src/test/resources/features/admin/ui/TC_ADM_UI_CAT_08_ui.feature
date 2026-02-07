@TC_ADM_UI_CAT_08
Feature: Admin Category Management - Search and Filter

  Scenario: Verify search and filter functionality for Fruits
    Given Admin is logged into the system
    And Admin navigates to the category management list
    When Admin enters search term "banana" in search box
    And Admin selects parent "Fruits" from the dropdown
    And Admin clicks Search
    Then List updates to show only matching categories
    When Admin clicks Reset
    Then List resets to show all categories