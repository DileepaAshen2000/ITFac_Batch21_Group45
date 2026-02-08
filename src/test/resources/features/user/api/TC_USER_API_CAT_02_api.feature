Feature: User cannot edit category

  @User
  @TC_USER_API_CAT_02
  Scenario: Verify User is forbidden from updating a category
    Given User is authenticated
    When User sends PUT request to update category with id 145
    Then User should receive access denied response
