@Admin @TC_ADM_UI_PLANT_03
Feature: Admin - Plant - Open Add Plant page

  Scenario: Verify Admin can open Add Plant page
    Given Admin is logged into the system
    When Admin navigates to plant list page for TC03
    And Admin clicks Add Plant button
    Then Add Plant form should be displayed
