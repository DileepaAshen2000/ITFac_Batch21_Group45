Feature: User can not add Sales

@User
@TC_USR_API_SALES_12
  Scenario: Verify User can't add sales
    Given User has a valid token
    And A valid plantId exists
    When User sends POST request to "/api/sales/plant/{plantId}" with quantity 1
    Then response status should be 403