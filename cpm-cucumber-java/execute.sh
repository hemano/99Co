#!/usr/bin/env bash
mvn clean test -Dcucumber.options="--tags @sanity"
mvn site
