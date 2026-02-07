@UserUi
@TC_USER_UI_CAT_03
Feature: User cannot edit or delete categories

  Scenario: Verify no edit or delete actions are available for User
    Given User is logged in and categories exist
    When User views the categories list
    Then No edit or delete actions should be available
