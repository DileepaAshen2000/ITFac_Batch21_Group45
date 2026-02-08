
@Admin @TC_ADM_UI_PLANT_06
Feature: Admin - Plant - Edit action visibility

  Scenario: Verify Edit option is visible for Admin on plant list
    Given Admin is logged into the system
    When Admin navigates to plant list page for TC06
    Then Edit option should be visible for a plant row
