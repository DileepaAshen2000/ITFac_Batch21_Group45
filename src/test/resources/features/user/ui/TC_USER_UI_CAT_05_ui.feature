@User
@TC_USER_UI_CAT_05
Feature: User Category Name Sorting

  Scenario: Verify sorting by Name works for User
    Given User is logged into the system
    When User navigates to category list page
    And User clicks the Name column header once
    And User clicks the Name column header again
    Then Categories should be sorted alphabetically then reverse-alphabetically by Name