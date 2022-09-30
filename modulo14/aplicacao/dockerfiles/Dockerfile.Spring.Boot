#
# Build Stage
#
FROM maven:3.6.3-openjdk-11-slim AS build

COPY backend/target /home/app/target

#Apagando os arquivos, exceto o .jar
RUN bash -O extglob -c 'rm -rfv /home/app/target/!(*.jar)'


#
# Package stage
#
FROM openjdk:11-slim
COPY --from=build /home/app/target/*.jar /usr/local/lib/springbootapi.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/springbootapi.jar"]