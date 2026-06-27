FROM eclipse-temurin:18-jdk AS build
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN chmod +x gradlew && ./gradlew build --no-daemon || return 0
COPY src ./src
RUN ./gradlew build --no-daemon

FROM eclipse-temurin:18-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
