@UserUi
@TC_USER_UI_CAT_03
Feature: Category delete button not visible for User

  Scenario: Verify Delete button is visible for user
    Given User is logged into the system as an user
    When User navigates to the category list page
    Then The Delete butto not visible in the actions column
