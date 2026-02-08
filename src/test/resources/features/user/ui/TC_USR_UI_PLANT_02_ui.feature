@User @TC_USR_UI_PLANT_02
Feature: User - Plant - Add button hidden

  Scenario: Verify Add Plant button hidden for User
    Given User is logged into the system
    When User navigates to plant list page for TC02
    Then Add Plant button should NOT be visible for User
