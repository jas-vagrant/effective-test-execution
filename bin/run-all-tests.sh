#!/bin/bash

# Bash script to run all tests in the test suite.

cd ..
mvn clean test -Prun-all-tests