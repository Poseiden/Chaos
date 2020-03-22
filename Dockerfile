FROM openjdk:11.0-jdk-slim

WORKDIR /app

COPY build/libs/Chaos-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]
