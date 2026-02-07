Feature: Admin can see sell plant button

@Admin
@TC_ADM_UI_SALES_12
  Scenario: Verify Sell Plant button visible
    Given Admin is logged into the system
    When Admin navigates to Sales page "/ui/sales"
    Then Sell Plant button should be visible