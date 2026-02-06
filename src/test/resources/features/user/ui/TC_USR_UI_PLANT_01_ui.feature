@User @TC_USR_UI_PLANT_01
Feature: User - Plant - Access list page

  Scenario: Verify User can access plant list
    Given User is logged into the system
    When User navigates to plant list page for TC01
    Then Plant list page should be visible for User
