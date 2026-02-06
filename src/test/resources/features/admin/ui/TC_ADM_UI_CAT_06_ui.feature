Feature: Admin Category Management

  @Admin
  @TC_ADM_UI_CAT_06
  Scenario: Verify editing an existing category as Admin
    Given Admin is logged into the system
    And Admin navigates to category list page
    When Admin clicks edit icon for an existing category
    And Admin updates category name
    And Admin clicks save button
    Then Admin should be redirected to category list page
    And Updated category should be displayed in the list
