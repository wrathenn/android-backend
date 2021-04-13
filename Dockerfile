FROM maven:3.6-jdk-11-slim AS build
COPY src /home/androidBackendApp/src
COPY pom.xml /home/androidBackendApp/
RUN mvn -f /home/androidBackendApp/pom.xml clean package

FROM openjdk:8-jdk-alpine
COPY --from=build /home/androidBackendApp/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]