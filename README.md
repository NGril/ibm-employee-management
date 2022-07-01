# ibm-employee-management

Sample employee management application.

## Requirements

- JDK 17+
- Maven 3.8+
- a Java IDE (Intelij IDEA preferred)

## Locally running the project:

- `mvn clean install`
- set the active profile to `dev` in the [application.yml](./src/main/resources/application.yml)
- run the App using either: 
  - `mvn spring-boot:run` 
  - `mvn clean package` and `java -jar target/ibm-employee-management.jar`
- to test the app you can download the included
  Postman [collection](.postman/IBM-EMPLOYEE-MANAGEMENT.postman_collection.json)
  and [environment](.postman/IBM-EMPLOYEE-MANAGEMENT-dev.postman_environment.json)
- h2 database, which is used in development mode, can also be accessed on the `/h2-console` path (credentials and jdbc
  url can be found in [application-dev.yml](./src/main/resources/application-dev.yml)) 

## Locally running with docker
- `docker build --tag=ibm-employee-management:latest .`
- `docker run -p8888:8080 ibm-employee-management:latest`
- with this the app is running in the docker container on port 8888