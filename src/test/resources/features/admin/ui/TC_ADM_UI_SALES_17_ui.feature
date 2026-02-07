Feature: mandatory field validation

@Admin
@TC_ADM_UI_SALES_17
  Scenario: Verify mandatory field validation on Sell Plant form
    Given Admin is logged into the system
    And Admin is on Sell Plant page "/ui/sales/new"
    When Admin leaves Select Plant empty
    And Admin leaves Quantity empty
    And Admin clicks Sell button
    Then Validation errors should be displayed for required fields