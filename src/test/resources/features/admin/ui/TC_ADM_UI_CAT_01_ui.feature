Feature: Admin Category List

  @TC_ADM_UI_CAT_01
  Scenario: Verify category list displays correctly
    Given Admin is logged into the system
    When Admin navigates to category list page
    Then Category list table should be displayed
