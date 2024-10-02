FROM openjdk:17-jdk-alpine
LABEL authors="dghay"
WORKDIR '/app'
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]