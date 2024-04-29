# Cucumber-bdd-api-framework

## **Overview:**
API is the acronym for Application Programming Interface, which is a software intermediary that allows two applications to talk to each other.  This API framework is developed using REST Assured and Cucumber.  REST Assured is a Java library that provides a domain-specific language (DSL) for writing powerful, maintainable tests for RESTful APIs. Cucumber is an open source library, which supports behavior driven development. To be more precise, Cucumber can be defined as a testing framework, driven by plain English text. It serves as documentation, automated tests, and a development aid all in one.

### **Some of the key features of this framework:**

1. It generates Cucumber HTML report with all the step details.
2. Generates execution logs, with detailed request and response details.
3. Feature file has examples of reading request details from json file.
4. This also has an example to validate response body using json schema
5. Test execution can be triggered form command line and from TestRunner.java file as Junit Tests also from pom.xml file. 

## **Required Setup :**

- [Java] should be installed and configured.
- [Maven] should be installed and configured.
- Update the project once cloned

## **Running Test:**

Open the command prompt and navigate to the folder in which pom.xml file is present.
1. Open pom.xml file right click and run as maven test/install
2. Open TestRunner.java file and right click and run as Junit test.

Run the below Maven command.

    mvn clean test


Once the execution completes report & log will be generated in below folder.

**Report:** 	*target/cucumber-html-reports/overview-features.html*<br>
**Log:** 		*target/logs*