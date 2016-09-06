Feature: Projects API

  @api-tests @sanity
  Scenario Outline:GET Request - List projects
    Given User sends a GET request to "/api/projects" with <developer-id> as parameter
    Then  The status code should be <code>
    Then Verify following name and status exists
      | name               | status   |
      | Embarcadero Suites | inactive |
      | Millenium Village  | active   |
      | Holland Village    | active   |
    Examples: developer ids
      | developer-id | code |
      | 2            | 200  |
      | qw           | 200  |


#  Scenario:POST Request - Rail journey search with valid details
#    Given User sends a POST request to "/mticket/search" with following details
#      |key        |value    |
#      |origin     |1000011  |
#      |destination|1000302  |
#    Then  The status code should be 200
#    And   The Journey count should be 3
#    And   The trip should have vehicle-id "IC 857" and name "InterCity Train" and not Cancelled
#    And   The vehicle name language code should have codes "us,da,gb"