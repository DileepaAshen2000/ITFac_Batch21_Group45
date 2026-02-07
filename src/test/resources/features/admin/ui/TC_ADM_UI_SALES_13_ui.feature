Feature: Admin can navigate to sell plant

@Admin
@TC_ADM_UI_SALES_13
  Scenario: Verify Navigation to Sell Plant page
    Given Admin is logged into the system
    When Admin navigates to Sales page "/ui/sales"
    And Admin clicks Sell Plant button
    Then Sell Plant form should be displayed on "/ui/sales/new"