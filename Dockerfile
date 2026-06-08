FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn -Dfile.encoding=UTF-8 -Dmaven.test.skip=true package

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]