@UserUi
@TC_USER_UI_CAT_04
Feature: User pagination functionality

  Scenario: Verify pagination works for User
    Given User is logged in and multiple pages of categories exist
    When User navigates to the category list
    And User clicks the Next button
    Then Table updates to show next page and current page is highlighted
    When User clicks the Previous button
    Then Table updates to show previous page and current page is highlighted