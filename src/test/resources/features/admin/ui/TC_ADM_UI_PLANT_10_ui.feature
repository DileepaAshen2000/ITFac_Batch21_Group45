
@Admin @TC_ADM_UI_PLANT_10
Feature: Admin - Plant - Cancel button on Add/Edit

  Scenario: Cancel redirects back to plant list
    Given Admin is logged into the system
    When Admin navigates to plant list page for TC10
    And Admin opens add plant page
    And Admin clicks cancel on plant form
    Then Admin should be redirected to plant list page
