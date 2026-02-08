
@User @TC_USR_UI_PLANT_06
Feature: User - Plant - Sorting

  Scenario: User sorts plant list by Name
    Given User is logged into the system
    When User navigates to plant list page
    And User sorts plants by name
    Then Plant list should be sorted alphabetically by name
