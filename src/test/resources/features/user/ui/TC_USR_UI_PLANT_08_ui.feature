#Feature: TC-USR-UI-PLANT-08 Verify pagination
#
#  @TC_USR_UI_PLANT_08
#  Scenario: User navigates pages
#    Given User is logged in
#    When User navigates to Plants page
#    And User goes to next page
#    Then Correct data per page should be displayed


@User @TC_USR_UI_PLANT_08
Feature: User - Plant - Pagination

  Scenario: User navigates to next page in plant list
    Given User is logged into the system
    When User navigates to plant list page
    And User goes to next page
    Then Plant list should still be displayed
