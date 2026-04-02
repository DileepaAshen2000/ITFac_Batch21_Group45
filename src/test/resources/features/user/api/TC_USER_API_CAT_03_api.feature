Feature: User cannot delete category

  @User
  @TC_USER_API_CAT_03
  Scenario: Verify User is forbidden from deleting category
    Given User is authenticated
    When User sends DELETE request to delete category with id 37
    Then User should receive forbidden delete response
