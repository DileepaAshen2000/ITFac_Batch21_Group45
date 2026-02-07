@UserUi
@TC_USER_UI_CAT_05
Feature: Empty category list message

  Scenario: Verify no category found message displays when list is empty
    Given User is logged in and no categories exist in the system
    When User navigates to categories page
    Then No category found message should be displayed
    And Message text should contain "No categories"