$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/googleTest.feature");
formatter.feature({
  "line": 1,
  "name": "Test the framework",
  "description": "",
  "id": "test-the-framework",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4863048574,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Check the title after google search",
  "description": "",
  "id": "test-the-framework;check-the-title-after-google-search",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@sanity"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "User finds the title relevant to search keyword",
  "rows": [
    {
      "cells": [
        "searchKeyword",
        "firstName"
      ],
      "line": 6
    },
    {
      "cells": [
        "Milk",
        "Amul"
      ],
      "line": 7
    },
    {
      "cells": [
        "Butter",
        "Danone"
      ],
      "line": 8
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "GoogleTestSteps.User_finds_the_title_relevant_to_search_keyword(UserDetails\u003e)"
});
formatter.result({
  "duration": 6304686340,
  "status": "passed"
});
formatter.after({
  "duration": 304341,
  "status": "passed"
});
formatter.uri("features/manageTransaction.feature");
formatter.feature({
  "line": 1,
  "name": "As a User",
  "description": "I want to be able to see the Manage Transaction\nI should be able to filter Transactions",
  "id": "as-a-user",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2404544887,
  "status": "passed"
});
formatter.background({
  "line": 5,
  "name": "User needs to be on the Login page",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 6,
  "name": "user is on Login Page",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "User logs in to mConsole",
  "keyword": "When "
});
formatter.match({
  "location": "StartingSteps.User_logs_in_to_mConsole()"
});
formatter.result({
  "duration": 3984505197,
  "status": "passed"
});
formatter.match({
  "location": "LoginPageSteps.User_logs_in_to_mConsole()"
});
formatter.result({
  "duration": 4817357919,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "Login to mConsole",
  "description": "",
  "id": "as-a-user;login-to-mconsole",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 11,
  "name": "navigates to Manage Transactions",
  "keyword": "And "
});
formatter.match({
  "location": "LandingPageSteps.User_logs_in_to_mConsole()"
});
formatter.result({
  "duration": 6178220623,
  "error_message": "java.lang.AssertionError: \nExpected: is \"blah!!\"\n     but: was \"mConsole - Administrative console\"\n\tat org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:20)\n\tat org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:8)\n\tat steps.applicationSteps.LandingPageSteps.User_logs_in_to_mConsole(LandingPageSteps.java:31)\n\tat ✽.And navigates to Manage Transactions(features/manageTransaction.feature:11)\n",
  "status": "failed"
});
formatter.after({
  "duration": 164376549,
  "status": "passed"
});
});