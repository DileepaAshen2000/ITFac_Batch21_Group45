@User
@TC_USER_UI_CAT_02
Feature: User cannot add category

  Scenario: Verify no Add Category button is visible for User
    Given User is logged in as regular user
    When User navigates to categories page
    Then Add Category button should not be visible
