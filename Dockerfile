FROM maven:3.8.5-openjdk-17
ARG APP_ROOT=/usr/src/app
WORKDIR ${APP_ROOT}
COPY . ./
RUN mvn clean package
ENTRYPOINT ["java","-jar","./target/ibm-employee-management.jar"]

MAINTAINER Niko Gril
