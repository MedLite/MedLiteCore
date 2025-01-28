FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/MedLite-Core-*.jar app.jar   

# Optional: Expose a specific port if needed
EXPOSE 9099

ENV PROFILE=prod

CMD ["java", "-Dspring.profiles.active=$PROFILE", "-jar", "/app/app.jar"]