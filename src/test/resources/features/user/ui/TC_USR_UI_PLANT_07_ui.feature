#Feature: TC-USR-UI-PLANT-07 Verify Low badge visibility
#
#  @TC_USR_UI_PLANT_07
#  Scenario: Low badge appears for quantity < 5
#    Given User is logged in
#    When User navigates to Plants page
#    Then Low badge should be visible for low stock plants


@User @TC_USR_UI_PLANT_07
Feature: User - Plant - Low badge

  Scenario: Low badge appears for quantity less than 5
    Given User is logged into the system
    When User navigates to plant list page
    Then Low badge should be visible for low stock plants
