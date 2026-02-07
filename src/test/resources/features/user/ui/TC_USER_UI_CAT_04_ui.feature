@User @UI @TC_USER_UI_CAT_04
Feature: User Category Permissions

  Scenario: Verify edit category option is not available for standard User
    Given User is logged into the system as a standard user
    When User navigates to the category list page
    Then The Edit category option should not be displayed in the actions column