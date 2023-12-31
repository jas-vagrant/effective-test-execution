#!/bin/bash


source get-local-changes.sh

cp src/main/resources/source-tests-map.json bin/tests-map.json

filename="bin/modified-methods.json"
testFile="bin/tests-map.json"

json=$(cat $filename)

# Extract values corresponding to "key" into an array
array=($(echo $json | jq -r '.key[]'))

mvntest=""
# Print the array elements
for element in "${array[@]}"; do
    json2=$(cat $testFile)
    testmethods=($(echo "$json2" | jq -r --arg key "$element" '.[$key][]'))
    for testmethod in "${testmethods[@]}"; do
        echo "Test Method: $testmethod"
        className="${testmethod%.*}"
        method="${testmethod##*.}"
        fullTestName="${className}#${method}"
        mvntest+=$fullTestName", "
    done
done

mvntest="${mvntest%,}"

mvn clean test -Prun-impacted-tests -Dtest="$mvntest"