FROM maven:3.8.6-amazoncorretto-17 AS build
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:go-offline
COPY src /build/src/
RUN mvn package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=/build/target/*.jar
RUN mkdir -p /opt/profiler
COPY --from=build $JAR_FILE /opt/profiler/statistics.jar
ENTRYPOINT ["java", "-jar", "/opt/profiler/statistics.jar"]
