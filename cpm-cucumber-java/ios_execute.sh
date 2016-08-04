#!/usr/bin/env bash
mvn clean test -Dcucumber.options="--tags @ios" -Dbrowser=ios_driver
mvn site
