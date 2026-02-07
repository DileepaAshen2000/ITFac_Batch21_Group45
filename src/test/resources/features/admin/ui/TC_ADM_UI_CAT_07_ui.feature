@TC_ADM_UI_CAT_07
Feature: Admin Category Management - Delete Category

  Scenario: Verify deleting an existing category as Admin
    Given Admin is logged into the system
    And Admin navigates to category list page
    When Admin clicks delete icon for a category
    And Admin confirms the deletion
    Then Category should be removed from the list