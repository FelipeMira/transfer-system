# AS <NAME> to name this stage as maven
FROM maven:3.6.3-jdk-11-slim AS build

WORKDIR /usr/src/application
COPY . /usr/src/application
# Compile and package the application to an executable JAR
RUN mvn -f /usr/src/application/pom.xml clean package

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre as application

ARG JAR_FILE=api-bacen.jar

WORKDIR /opt/application
EXPOSE 8082
# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=build /usr/src/application/target/${JAR_FILE} /opt/application/

ENTRYPOINT ["java","-jar","api-bacen.jar"]