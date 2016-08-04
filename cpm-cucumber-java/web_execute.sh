#!/usr/bin/env bash
mvn clean test -Dcucumber.options="--tags @web"
mvn site
