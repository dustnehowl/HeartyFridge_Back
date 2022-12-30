FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY build/libs/test-0.0.1-SNAPSHOT.jar test.jar
ENTRYPOINT ["java","-jar","test.jar"]