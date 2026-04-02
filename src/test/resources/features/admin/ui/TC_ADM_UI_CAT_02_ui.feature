Feature: Admin Category Management

  @Admin
  @TC_ADM_UI_CAT_02
  Scenario: Verify adding a new main category as Admin
    Given Admin is logged into the system
    When Admin navigates to add category page
    And Admin enters a valid category name "MNO21"
    And Admin clicks save button
    Then Category should be created successfully
