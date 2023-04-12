FROM openjdk:17-alpine as build
WORKDIR /app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew build -x test

FROM openjdk:17-alpine as base
WORKDIR /app
COPY --from=build /app/build/libs/Commerce-0.0.1-SNAPSHOT.jar ./
ENTRYPOINT ["java", "-jar", "Commerce-0.0.1-SNAPSHOT.jar"]


