Feature: Unvailable plants are not visible

@Admin
@TC_ADM_UI_SALES_15
  Scenario: Verify unavailable plants in stock are not visible
    Given Admin is logged into the system
    And At least one plant has stock equal to 0
    When Admin navigates to Sales page "/ui/sales"
    And Admin clicks Sell Plant button
    And Admin opens Select Plant dropdown
    Then Plants with zero stock should not be shown