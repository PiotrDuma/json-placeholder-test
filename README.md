# json-placeholder-test

[Web service](https://jsonplaceholder.typicode.com/users) REST api test with jUnit5, RestAssured, and AssertJ. Test methods are implemented as a Java service class to simplify test cases and centralize maintenance within a single class.

## RUN

To run unit test open CLI, navigate to project's folder and then execute maven plugin

~~~
mvn clean test
~~~

## Features

- unit tests with RestAssured;
- parallel testing with jUnit5;
- JsonService class to manage web requests;
- UserDto class to handle server json response;
