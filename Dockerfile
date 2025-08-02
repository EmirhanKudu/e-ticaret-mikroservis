FROM openjdk:21
COPY target/e-ticaret-kullanici-0.0.1-SNAPSHOT.jar app.jar
LABEL authors="ekudu"

ENTRYPOINT ["java","-jar","/app.jar"]