Feature: Admin can cancel delete

@Admin
@TC_ADM_UI_SALES_19
  Scenario: Verify Admin can cancel delete
    Given Admin is logged into the system
    And Sales list contains at least one sale
    When Admin navigates to Sales page "/ui/sales"
    And Admin clicks delete button for a sale
    And Admin cancels delete action
    Then Admin should remain on Sales page "/ui/sales"
    And Sale should not be removed from Sales table