Feature: available plants are visible

@Admin
@TC_ADM_UI_SALES_14
  Scenario: Verify available plants in stocks are visible
    Given Admin is logged into the system
    And At least one plant has stock greater than 0
    When Admin navigates to Sales page "/ui/sales"
    And Admin clicks Sell Plant button
    And Admin opens Select Plant dropdown
    Then Only plants with available stock should be shown