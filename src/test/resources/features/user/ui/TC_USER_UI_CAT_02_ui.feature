@User @TC_USER_UI_CAT_02
Feature: User Category Navigation

  Scenario: Validate clicking Categories link navigates to Category List page
    Given User is logged into the system
    When User clicks on the "Categories" menu link
    Then the Category List page should load successfully
    And all categories should be visible in the table