# EFFECTIVE-TEST-EXECUTION

This is a sample application project to demonstrate the execution of impacted tests 
(tests that cover the source code change) instead of running the whole test suite.
This is achieved by instrumenting the source code changes using a custom java agent - 
[byte-buddy-agent](https://github.com/TV-hackathon-2023/byte-busters). Thsi custom agent generates 
a `source-tests-map.json` containing source-code-methods mapped to corresponding list of covering test-methods.
With the help of bash script `run-impacted-test.sh` one can now run only the impacted tests based on the modifications done in the sourceCode in local.



### Steps to run impacted tests :
**Step 1 :** Add [byte-buddy-agent.jar](https://github.com/TV-hackathon-2023/byte-busters) path in `pom.xml` 
as shown in below example -
```
<properties>
    <bytebuddy.agent.path>
        {your-local-path-to-custom-byte-buddy-agent.jar}
    </bytebuddy.agent.path>
 </properties>
```

**Step 2 :** Execute the bash script `run-all-tests.sh` to generate `source-tests-map.json`. 
It is generated at path `src/main/resources`. (It can be maintained unchanged in the `master` branch until new tests are added).

**Step 3 :** Make changes in the src code and then execute the bash script `run-impacted-test.sh` to run only the impacted tests.
