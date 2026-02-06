@Admin @TC_ADM_UI_PLANT_04
Feature: Admin - Plant - Mandatory validation

  Scenario: Verify mandatory field validation on Add Plant
    Given Admin is logged into the system
    When Admin navigates to plant list page for TC04
    And Admin clicks Add Plant button
    And Admin submits plant form without entering data
    Then Validation errors should be shown on plant form
