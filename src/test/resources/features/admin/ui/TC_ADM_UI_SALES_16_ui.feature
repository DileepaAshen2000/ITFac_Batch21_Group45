Feature: Admin can sell plant with valid data

@Admin
@TC_ADM_UI_SALES_16
  Scenario: Verify Admin can add Sell with valid data
    Given Admin is logged into the system
    And Admin is on Sell Plant page "/ui/sales/new"
    And Plant stock exists for selected plant
    When Admin selects a valid plant
    And Admin enters a valid quantity
    And Admin clicks Sell button
    Then Admin should be redirected to Sales page "/ui/sales"
    And Sale should be added to Sales table
    And Success message should be displayed