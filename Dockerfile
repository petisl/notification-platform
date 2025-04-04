#Build environment
FROM maven:3.6.3-openjdk-11-slim AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn -B -f ./pom.xml clean package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#Production packaging
FROM adoptopenjdk/openjdk11:jre-11.0.10_9-alpine

ARG DEPENDENCY=target/dependency
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/notification-service.jar  /app/alaje-notification-service.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "alaje-notification-service.jar"]
