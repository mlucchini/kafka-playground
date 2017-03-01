FROM openjdk:8-jdk-alpine

WORKDIR /app
ADD build/libs/voting-0.0.1-SNAPSHOT.jar voting.jar

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java -Dserver.port=8080 -jar /app/voting.jar" ]
