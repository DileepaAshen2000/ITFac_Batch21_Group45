Feature: Admin can view sale list

@Admin
@TC_ADM_UI_SALES_11
  Scenario: Verify Admin can view sales list
    Given Admin is logged into the system
    When Admin navigates to Sales page "/ui/sales"
    Then Sales list should be displayed