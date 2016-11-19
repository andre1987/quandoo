This is a small project that contains tests for practical exercise of quandoo.com

Supported operating systems are: **Windows** and **Linux**.

Supported browsers are: **Chrome**, **Firefox**.

To run tests:
mvn clean install -Plocal -Dos=windows|linux -Dbrowser=chrome|firefox

Run the following command to generate javadoc for test classes:
mvn javadoc:test-javadoc
