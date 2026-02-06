@User @TC_USR_UI_PLANT_04
Feature: User - Plant - Search

  Scenario: Verify User can search plants
    Given User is logged into the system
    When User navigates to plant list page for TC04
    And User searches plant by keyword "Aloe"
    Then Matching plant results should be shown for keyword "Aloe"
