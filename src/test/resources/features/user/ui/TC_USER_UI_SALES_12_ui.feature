Feature: User can not see sell plant button

@User
@TC_USR_UI_SALES_12
  Scenario: Verify Sell Plant button not visible
    Given User is logged into the system
    When User navigates to Sales page "/ui/sales"
    Then Sell Plant button should not be visible