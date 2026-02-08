Feature: User get categories when none exist

  @User
  @TC_USER_API_CAT_04
  Scenario: Verify empty categories response for User
    Given User is authenticated
    When User sends GET request to fetch categories
    Then User should receive empty categories response
