FROM openjdk:11.0-jdk-slim

WORKDIR /app

COPY build/libs/java_program_initial_with_gradle.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]
