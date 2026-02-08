
@User @TC_USR_UI_PLANT_10
Feature: User - Plant - Reset button

  Scenario: Reset clears search and arranges list alphabetically
    Given User is logged into the system
    When User navigates to plant list page
    And User searches plant with keyword "Aloe"
    And User clicks reset
    Then Plant list should be sorted alphabetically by name
