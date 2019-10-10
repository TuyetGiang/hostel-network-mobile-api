FROM openjdk:8-jre-alpine AS base
WORKDIR /app
EXPOSE 8080

FROM openjdk:8-jdk-alpine AS build
COPY [".", "/tmp/"]
WORKDIR /tmp
RUN chmod +x gradlew
RUN rm ./src/main/resources/application.properties
RUN mv ./src/main/resources/application.prod.properties ./src/main/resources/application.properties
RUN ./gradlew clean
RUN ./gradlew build

FROM base
WORKDIR /app
COPY --from=build /tmp/build/libs/*.war app.war
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.war"]