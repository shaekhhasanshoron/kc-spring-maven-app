FROM maven:3.8.5-openjdk-17 AS builder
COPY src /home/app/src
ADD pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests -U -e

FROM openjdk:17-jdk
RUN mkdir /app
COPY --from=builder /home/app/target/*.jar /app/app.jar
EXPOSE 8080
EXPOSE 8081
ENTRYPOINT exec java -Dspring.profiles.active=prod -jar /app/app.jar
