@User @API
Feature: User Category API Management

  Background:
    Given User is authorized with a valid token

  @TC_USER_API_CAT_01
  Scenario: Verify get categories API
    When User calls "GET" request to "/categories"
    Then Response status code should be 200
    And Response should contain a list of all categories

  @TC_USER_API_CAT_02
  Scenario: Verify search category by name API
    When User calls "GET" request to "/categories" with parameter "name" as "Vegetables"
    Then Response status code should be 200
    And All returned categories should have name containing "Vegetables"

  @TC_USER_API_CAT_03
  Scenario: Verify filter by parent category API
    When User calls "GET" request to "/categories" with parameter "parent" as "Vegetables"
    Then Response status code should be 200
    And All returned categories should belong to parent "Vegetables"

  @TC_USER_API_CAT_04
  Scenario: Verify sorting categories by ID API
    When User calls "GET" request to "/categories" with parameter "sort" as "id"
    Then Response status code should be 200
    And Categories should be sorted numerically by ID

  @TC_USER_API_CAT_05
  Scenario: Verify sorting categories by Name API
    When User calls "GET" request to "/categories" with parameter "sort" as "name"
    Then Response status code should be 200
    And Categories should be sorted alphabetically by Name