FROM openjdk:20-ea-17-jdk
VOLUME /tmp
ADD ./src/main/resources/static /src/main/resources/static
COPY build/libs/test-0.0.1-SNAPSHOT.jar test.jar
ENTRYPOINT ["java","-jar","test.jar"]