Feature: Test the framework

  @sanity
  Scenario: Check the title after google search
    Given User finds the title relevant to search keyword
          |searchKeyword|firstName|
          |Milk         |Amul     |
          |Butter       |Danone   |