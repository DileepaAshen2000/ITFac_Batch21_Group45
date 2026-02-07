Feature: Admin can delete sales

@Admin
@TC_ADM_UI_SALES_18
  Scenario: Verify Admin can delete sales
    Given Admin is logged into the system
    And Sales list contains at least one sale
    When Admin navigates to Sales page "/ui/sales"
    And Admin clicks delete button for a sale
    And Admin confirms delete action
    Then Delete successful message should be displayed
    And Sale should be removed from Sales table