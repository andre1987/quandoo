This is a small project that contains tests for practical exercise of quandoo.com

###Pre-requisites###
  * Java 8
  * Maven 3 or greater

###Supported operating systems:###
  * Windows
  * Linux

###Supported browsers###
  * Chrome
  * Firefox

###Hot to run###
`mvn clean install -Plocal -Dos=windows|linux -Dbrowser=chrome|firefox`

###Test report###
Test report will be automatically generated after run all test and could be found in the:
`$PROJECT_DIR/target/surefire-reports/junit-noframes.html`

###Generate javadoc for test classes###
`mvn javadoc:test-javadoc`
