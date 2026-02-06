#Feature: TC-USR-UI-PLANT-09 Verify empty state
#
#  @TC_USR_UI_PLANT_09
#  Scenario: No plants found message shown
#    Given User is logged in
#    When User navigates to Plants page with no plants
#    Then "No plants found" message should be shown

@User @TC_USR_UI_PLANT_09
Feature: User - Plant - Empty state

  Scenario: No plants found message shown for invalid search
    Given User is logged into the system
    When User navigates to plant list page
    And User searches plant with keyword "___NO_SUCH_PLANT___"
    Then Empty state should be shown
