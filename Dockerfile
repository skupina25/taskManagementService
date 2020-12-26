FROM adoptopenjdk:8-jre-hotspot
RUN mkdir /app
WORKDIR /app
ADD target/taskManagementService-1.0.0-SNAPSHOT.jar /app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "taskManagementService-1.0.0-SNAPSHOT.jar"]
