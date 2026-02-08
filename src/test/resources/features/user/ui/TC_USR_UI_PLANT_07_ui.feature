
@User @TC_USR_UI_PLANT_07
Feature: User - Plant - Low badge

  Scenario: Low badge appears for quantity less than 5
    Given User is logged into the system
    When User navigates to plant list page
    Then Low badge should be visible for low stock plants
