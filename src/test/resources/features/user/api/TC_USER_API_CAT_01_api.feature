Feature: User cannot create category

  @User
  @TC_USER_API_CAT_01
  Scenario: Verify User is forbidden from creating a category
    Given User is authenticated
    When User sends POST request to create category
    Then User should receive forbidden response
