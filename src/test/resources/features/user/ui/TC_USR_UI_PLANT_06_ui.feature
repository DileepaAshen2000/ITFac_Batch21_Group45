#Feature: TC-USR-UI-PLANT-06 Verify User can sort plant list
#
#  @TC_USR_UI_PLANT_06
#  Scenario: User sorts by name or price or stock
#    Given User is logged in
#    When User navigates to Plants page
#    And User sorts the plant list
#    Then Sorted plant list should be displayed

@User @TC_USR_UI_PLANT_06
Feature: User - Plant - Sorting

  Scenario: User sorts plant list by Name
    Given User is logged into the system
    When User navigates to plant list page
    And User sorts plants by name
    Then Plant list should be sorted alphabetically by name
