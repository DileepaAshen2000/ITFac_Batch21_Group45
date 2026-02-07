@Admin @TC_ADM_UI_CAT_02
Feature: Admin Category Management - Delete Category

  Scenario: Verify deleting a category as Admin
    Given Admin is logged into the system
    And Category exists and is not in use
    When Admin clicks delete icon for a category
    And Admin confirms the deletion message
    Then The category should be removed from the list
    And No error should be displayed
