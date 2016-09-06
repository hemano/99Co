Feature: Journey Search
  As a customer I should be able search trips with origin, destination
  departure-time

  @api-tests @sanity @dsb-mretail
  Scenario:POST Request - Rail journey search with valid details
    Given User sends a POST request to "/mticket/search" with "current" date and following details
          |key        |value    |
          |origin     |1062655  |
          |destination|1062656  |
    Then  The status code should be 200
    And   The Journey count should be ">" 0
    And   The Journey criteria should match the request details
          |key        |value    |
          |origin     |1062655  |
          |destination|1062656  |


#  Scenario:POST Request - Rail journey search with valid details
#    Given User sends a POST request to "/mticket/search" with following details
#      |key        |value    |
#      |origin     |1000011  |
#      |destination|1000302  |
#    Then  The status code should be 200
#    And   The Journey count should be 3
#    And   The trip should have vehicle-id "IC 857" and name "InterCity Train" and not Cancelled
#    And   The vehicle name language code should have codes "us,da,gb"