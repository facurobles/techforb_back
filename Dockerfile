FROM amazoncorretto:17-alpine-jdk

MAINTAINER Robles_Facundo

COPY target/techforb_back-0.0.1-SNAPSHOT.jar /app/techforb_back.jar

ENTRYPOINT ["java","-jar","/app/techforb_back.jar"]

EXPOSE 8080 

