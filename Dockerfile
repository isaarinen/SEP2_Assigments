# Build
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime
FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=builder /app/target/Week-1-Assignment-1.0-SNAPSHOT.jar .
COPY --from=builder /app/target/classes ./classes
COPY src/main/resources ./resources

ENTRYPOINT ["java", "-cp", "Week-1-Assignment-1.0-SNAPSHOT.jar:classes:resources", "org.example.Main"]