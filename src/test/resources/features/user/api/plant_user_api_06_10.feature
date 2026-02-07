@UserApi
Feature: User - Plants API (TC-USR-API-PLANT-06 to 10)

  @TC_USR_API_PLANT_06
  Scenario: Verify filter via API
    Given User API token is available
    And A valid categoryId is available from existing plants
    When User filters plants by category via API
    Then User should receive 200 OK with filtered results

  @TC_USR_API_PLANT_07
  Scenario: Verify sorting via API
    Given User API token is available
    When User requests paged plants sorted by "name"
    Then User should receive 200 OK and list should be sorted by "name"

  @TC_USR_API_PLANT_08
  Scenario: Verify pagination via API
    Given User API token is available
    When User requests paged plants with page "1"
    Then User should receive 200 OK with paginated response

  @TC_USR_API_PLANT_09
  Scenario: Verify Low badge condition in response
    Given User API token is available
    When User requests all plants
    Then User should receive 200 OK and at least one plant should have quantity less than 5 if data exists

  @TC_USR_API_PLANT_10
  Scenario: Verify empty plant list response
    Given User API token is available
    And Plant list is empty in test environment if possible
    When User requests all plants
    Then User should receive an empty array response
