
@User @TC_USR_UI_PLANT_08
Feature: User - Plant - Pagination

  Scenario: User navigates to next page in plant list
    Given User is logged into the system
    When User navigates to plant list page
    And User goes to next page
    Then Plant list should still be displayed
