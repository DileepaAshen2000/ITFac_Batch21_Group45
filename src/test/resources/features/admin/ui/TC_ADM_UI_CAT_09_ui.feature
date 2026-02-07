@TC_ADM_UI_CAT_09
Feature: Admin Category Management - Empty List Message

  Scenario: Verify no categories message displays when empty
    Given Admin is logged into the system
    And all categories are deleted
    When Admin navigates to "/ui/categories"
    Then the message "No category found" should be displayed instead of table rows