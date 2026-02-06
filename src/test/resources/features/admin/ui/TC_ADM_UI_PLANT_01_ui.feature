@Admin @TC_ADM_UI_PLANT_01
Feature: Admin - Plant - Access list page

  Scenario: Verify Admin can access plant list page
    Given Admin is logged into the system
    When Admin navigates to plant list page for TC01
    Then Plant list page should be visible for Admin
