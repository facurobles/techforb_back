FROM alpine:latest

CMD ["/bin/sh"]

MAINTAINER Robles_Facundo

COPY target/techforb_back-0.0.1-SNAPSHOT.jar techforb_back.jar

ENTRYPOINT ["java","-jar","/techforb_back.jar"]

EXPOSE 8080 

