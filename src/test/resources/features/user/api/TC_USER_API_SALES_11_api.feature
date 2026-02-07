Feature: User can fetch sales list

@User
@TC_USR_API_SALES_11
  Scenario: Verify User can fetch sales list
    Given User has a valid token
    When User sends GET request to "/api/sales"
    Then response status should be 200
    And response should contain sales list