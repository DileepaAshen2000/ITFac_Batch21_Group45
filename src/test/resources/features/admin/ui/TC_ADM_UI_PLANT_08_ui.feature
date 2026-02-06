#Feature: TC-ADM-UI-PLANT-08 Verify Delete action visibility for Admin
#
#  @TC_ADM_UI_PLANT_08
#  Scenario: Delete option is visible for Admin on plant list
#    Given Admin is logged in
#    And Admin navigates to Plants page
#    Then Delete option should be visible for a plant row


@Admin @TC_ADM_UI_PLANT_08
Feature: Admin - Plant - Delete action visibility

  Scenario: Verify Delete option is visible for Admin on plant list
    Given Admin is logged into the system
    When Admin navigates to plant list page for TC08
    Then Delete option should be visible for a plant row
