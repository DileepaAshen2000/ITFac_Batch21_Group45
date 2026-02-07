Feature: User can not delete sales

@User
@TC_USR_UI_SALES_13
  Scenario: Verify Delete action not visible
    Given User is logged into the system
    When User navigates to Sales page "/ui/sales"
    Then Delete button should not be visible