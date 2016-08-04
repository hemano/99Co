Feature: As a User
  I want to be able to see the Manage Transaction
  I should be able to filter Transactions

  @web @sanity
  Scenario:Login to mConsole
    Given user is on Login Page
    When User logs in to mConsole
    And User navigates to Manage Transactions
    Then User sees search option after entering number followed by + sign like "+457774441114" and click on it