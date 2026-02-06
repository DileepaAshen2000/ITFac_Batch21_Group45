@Admin @TC_ADM_UI_PLANT_05
Feature: Admin - Plant - Add plant with valid data

  Scenario: Verify Admin can add a plant with valid data
    Given Admin is logged into the system
    When Admin navigates to plant list page for TC05
    And Admin clicks Add Plant button
    And Admin enters valid plant data and submits
    Then Newly added plant should be visible in plant list
