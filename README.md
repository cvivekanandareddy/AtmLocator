# AtmInfo
This application is developed with Spring Boot. This will run on default port (8080) after running the application.

This application has Swagger UI. From this UI we can test the API's directly from the browser.

The Swagger UI url: http://localhost:8080/swagger-ui.html

In this application, I've added the actuator dependenct to check the health status of the application.

Actuator url: http://127.0.0.1:8080/actuator

API endpoints are as below:

  a)  http://localhost:8080/api/atmInfo/all - Get method

  b)  http://localhost:8080/api/atmInfo/byCity/{city} - Get method ==> need to pass city to get ATM's information
