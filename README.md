# json-placeholder-test

[Web service](https://jsonplaceholder.typicode.com/users) REST api test with jUnit5, RestAssured, and AssertJ. Test methods are implemented as a Java service class to simplify test cases and centralize maintenance within a single class.

## RUN

To run unit test open CLI, navigate to project's folder and then execute maven plugin

~~~
mvn clean test
~~~

## Features

- Unit tests with RestAssured;
- Parallel testing with jUnit5;
- JsonService class to manage web requests;
- UserDto class to handle server json response;
- Decorator pattern using wrapper of the server response object (decorator-wrapper branch);

## Review changes applied:
- Merge decorator pattern branch to wrap response object;
- Split models into separate classes;
- Apply JsonProperty annotation to map API variables;
- Add filters to log request/response;
