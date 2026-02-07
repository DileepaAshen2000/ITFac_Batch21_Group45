@User @TC_USER_UI_CAT_03
Feature: User Category Search

  Scenario: Verify search by category name
    Given User is logged into the system
    And User navigates to category list page
    When User enters "Vegetables" in the search box
    And User clicks the Search button
    Then only categories matching "Vegetables" should be displayed