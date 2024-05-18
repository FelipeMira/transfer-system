# AS <NAME> to name this stage as maven
FROM maven:3.6.3-jdk-11-slim AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn -f /usr/src/app/pom.xml clean package

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre as java

ARG JAR_FILE=api-cadastro.jar

WORKDIR /opt/app
EXPOSE 8081
# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","api-cadastro.jar"]