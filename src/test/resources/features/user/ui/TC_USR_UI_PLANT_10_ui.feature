#Feature: TC-USR-UI-PLANT-10 Verify Reset button
#
#  @TC_USR_UI_PLANT_10
#  Scenario: Reset arranges alphabetically after sort/filter
#    Given User is logged in
#    When User navigates to Plants page
#    And User sorts or filters the list
#    And User clicks reset
#    Then Plants list should be arranged alphabetically

@User @TC_USR_UI_PLANT_10
Feature: User - Plant - Reset button

  Scenario: Reset clears search and arranges list alphabetically
    Given User is logged into the system
    When User navigates to plant list page
    And User searches plant with keyword "Aloe"
    And User clicks reset
    Then Plant list should be sorted alphabetically by name
