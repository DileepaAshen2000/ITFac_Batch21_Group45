Feature: User can combine search and filter parameters

  @User
  @TC_USER_API_CAT_01
  Scenario: Verify User can search and filter categories together
    Given User is authenticated
    When User sends GET request with search "DEF" and parent "ABD"
    Then User should receive filtered category list
