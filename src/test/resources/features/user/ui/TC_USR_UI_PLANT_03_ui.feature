@User @TC_USR_UI_PLANT_03
Feature: User - Plant - Edit/Delete hidden

  Scenario: Verify Edit/Delete hidden for User
    Given User is logged into the system
    When User navigates to plant list page for TC03
    Then Edit and Delete actions should NOT be visible for User
