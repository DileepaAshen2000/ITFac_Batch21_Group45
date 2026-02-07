Feature: User can sort list

@User
@TC_USR_UI_SALES_14
  Scenario: Verify User can sort Sales list
    Given User is logged into the system
    And Sales list has multiple records
    When User navigates to Sales page "/ui/sales"
    And User clicks Quantity column header
    Then Sales list should be sorted by quantity ascending or descending