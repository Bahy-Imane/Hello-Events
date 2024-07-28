FROM openjdk:21
EXPOSE 8080
ADD target/hello-event-backend.jar hello-event-backend.jar
ENTRYPOINT ["java","-jar","hello-event-backend.jar"]