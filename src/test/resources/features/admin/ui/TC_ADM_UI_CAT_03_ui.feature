Feature: Admin Sub Category Management

  @TC_ADM_UI_CAT_03
  Scenario: Verify adding a sub category as Admin
    Given Admin is logged into the system
    When Admin navigates to add category page for sub category
    And Admin enters sub category name "GHI1"
    And Admin selects parent category "DEF"
    And Admin saves the sub category
    Then Sub category should be created successfully
