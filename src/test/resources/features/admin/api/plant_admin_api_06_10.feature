@AdminApi
Feature: Admin - Plants API (TC-ADM-API-PLANT-06 to 10)

  @TC_ADM_API_PLANT_06
  Scenario: Validate quantity cannot be negative
    Given Admin API token is available
    When Admin creates a plant with negative quantity
    Then Admin should receive a validation error response

  @TC_ADM_API_PLANT_07
  Scenario: Verify Admin can update plant
    Given Admin API token is available
    And An existing plant is available for Admin API
    When Admin updates the plant via API
    Then Admin should receive 200 OK for update

  @TC_ADM_API_PLANT_08
  Scenario: Verify Admin can delete plant
    Given Admin API token is available
    And An existing plant is available for Admin API
    When Admin deletes the plant via API
    Then Admin should receive 204 No Content for delete

  @TC_ADM_API_PLANT_09
  Scenario: Verify filtering by category
    Given Admin API token is available
    And A valid categoryId is available from existing plants
    When Admin filters plants by category via API
    Then Admin should receive 200 OK with filtered results

  @TC_ADM_API_PLANT_10
  Scenario: Verify sorting via API
    Given Admin API token is available
    When Admin requests paged plants sorted by "price"
    Then Admin should receive 200 OK and list should be sorted by "price"
