Feature: User can see sales list

@User
@TC_USR_UI_SALES_11
  Scenario: Verify User can access Sales list
    Given User is logged into the system
    When User navigates to Sales page "/ui/sales"
    Then Sales list should be displayed