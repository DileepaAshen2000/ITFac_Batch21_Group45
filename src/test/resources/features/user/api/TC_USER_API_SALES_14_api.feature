Feature: User can sort list

@User
@TC_USR_API_SALES_14
  Scenario: Verify sorting via API
    Given User has a valid token
    When User sends GET request to "/api/sales?sort=plant"
    Then response status should be 200
    And response should contain sorted sales list by "plant"