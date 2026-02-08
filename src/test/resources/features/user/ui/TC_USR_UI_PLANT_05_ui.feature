@User @TC_USR_UI_PLANT_05
Feature: User - Plant - Filter by category

  Scenario: Verify User can filter by category
    Given User is logged into the system
    When User navigates to plant list page for TC05
    And User applies a category filter
    Then Plant list should be filtered by selected category
