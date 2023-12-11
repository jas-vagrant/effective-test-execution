# EFFECTIVE-TEST-EXECUTION

This is a sample application project to demonstrate the execution of impacted tests 
(tests that cover the source code change) instead of running the whole test suite.
This is achieved by instrumenting the source code changes using a custom java agent - 
[byte-buddy-agent](https://github.com/TV-hackathon-2023/byte-busters) which generates 
a `output.json` containing source-code-methods mapped to corresponding list of covering test-methods.
And then utilising the bash script `run-impacted-test.sh` run only the impacted tests.



### Steps to run impacted tests :
**Step 1 :** Add [byte-buddy-agent.jar](https://github.com/TV-hackathon-2023/byte-busters) path in `pom.xml` as java-agent 
in `maven-surefire-plugin`.

**Step 2 :** Execute `mvn clean test` to generate `output.json`. It is generated at path `src/main/resources`.

**Step 3 :** Move the `output.json` to the project root. (It can be maintained unchanged in the `master` branch until new 
tests are added).

**Step 4 :** Make changes in the src code and then execute the bash script `run-impacted-test.sh` to run only the impacted tests.