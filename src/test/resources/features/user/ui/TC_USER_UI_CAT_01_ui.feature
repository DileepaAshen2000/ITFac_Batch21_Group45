@User
@TC_USER_UI_CAT_01
Feature: User Category Sorting

  Scenario: Verify sorting by ID works for User
    Given User is logged into the system
    When User navigates to category list page
    And User clicks the ID column header once
    And User clicks the ID column header again
    Then Categories should be sorted descending then ascending by ID