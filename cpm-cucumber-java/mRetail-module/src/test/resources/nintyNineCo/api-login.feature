Feature: Login API

  @api-tests @sanity @login
  Scenario Outline:GET Request - Login user
    Given User sends a GET request to "/api/login" with <email> and <password>
    Then  The status code should be <code>
    And The message shoule be <message>
    And The role shoule be <role>
    Examples: developer ids
      | email      | password | code | message | role      |
      | dev1@99.co | dev1     | 200  | success | Developer |



#  Scenario:POST Request - Rail journey search with valid details
#    Given User sends a POST request to "/mticket/search" with following details
#      |key        |value    |
#      |origin     |1000011  |
#      |destination|1000302  |
#    Then  The status code should be 200
#    And   The Journey count should be 3
#    And   The trip should have vehicle-id "IC 857" and name "InterCity Train" and not Cancelled
#    And   The vehicle name language code should have codes "us,da,gb"