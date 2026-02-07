
@Admin @TC_ADM_UI_PLANT_09
Feature: Admin - Plant - Delete plant

  Scenario: Admin deletes a plant and it disappears from list
    Given Admin is logged into the system
    When Admin navigates to plant list page for TC09
    And Admin deletes the first plant and confirms
    Then Deleted plant should not be present in plant list
