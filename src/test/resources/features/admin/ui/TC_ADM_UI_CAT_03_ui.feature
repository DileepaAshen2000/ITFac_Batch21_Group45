Feature: Admin Sub Category Management

  @Admin
  @TC_ADM_UI_CAT_03
  Scenario: Verify adding a sub category as Admin
    Given Admin is logged into the system
    When Admin navigates to add category page for sub category
    And Admin enters sub category name "PQR20"
    And Admin selects parent category "ABD"
    And Admin saves the sub category
    Then Sub category should be created successfully
