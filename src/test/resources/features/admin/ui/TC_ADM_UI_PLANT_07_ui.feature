
@Admin @TC_ADM_UI_PLANT_07
Feature: Admin - Plant - Edit and update plant

  Scenario: Admin edits a plant and updates details
    Given Admin is logged into the system
    When Admin navigates to plant list page for TC07
    And Admin edits first plant and updates details
    Then Updated plant should be shown in plant list
