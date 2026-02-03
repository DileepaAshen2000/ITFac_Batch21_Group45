Feature: Admin Category Validation - Minimum Length

  @TC_ADM_UI_CAT_04
  Scenario: Verify validation error persists on attempt to submit with short name
    Given Admin is logged into the system
    When Admin navigates to add category page for validation
    And Admin enters short category name "AB"
    And Admin submits the add category form
    Then Validation error message should be displayed
    And Admin should remain on add category page
