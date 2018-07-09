FROM frolvlad/alpine-oraclejdk8:slim AS cc
VOLUME /tmp
ADD target/client5-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar",  "/app.jar" ]
