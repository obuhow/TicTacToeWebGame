FROM gradle:9.4-jdk18 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
RUN gradle build --no-daemon || return 0
COPY src ./src
RUN gradle build --no-daemon

FROM eclipse-temurin:18-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]