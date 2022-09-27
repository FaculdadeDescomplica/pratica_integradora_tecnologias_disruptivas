#
# Build Stage
#
FROM maven:3.6.3-openjdk-11-slim AS build

COPY backend/src /home/app/src
COPY backend/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:11-slim
COPY --from=build /home/app/target/*.jar /usr/local/lib/springbootapi.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/springbootapi.jar"]
