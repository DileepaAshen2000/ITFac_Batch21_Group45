@Admin @TC_ADM_UI_PLANT_02
Feature: Admin - Plant - Add button visibility

  Scenario: Verify Add Plant button visibility for Admin
    Given Admin is logged into the system
    When Admin navigates to plant list page for TC02
    Then Add Plant button should be visible for Admin
