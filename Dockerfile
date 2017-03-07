FROM openjdk:8-jdk-alpine

WORKDIR /app
ADD build/libs/playground-0.0.1-SNAPSHOT.jar playground.jar

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java -Dserver.port=8080 -jar /app/playground.jar" ]
