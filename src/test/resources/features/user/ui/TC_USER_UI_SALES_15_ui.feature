Feature: User can see no sales message

@User
@TC_USR_UI_SALES_15
  Scenario: Verify visibility of No Sales message
    Given User is logged into the system
    And No sales are available
    When User navigates to Sales page "/ui/sales"
    Then "No Sales Found" message should be displayed