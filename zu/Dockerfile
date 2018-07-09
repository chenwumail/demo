FROM frolvlad/alpine-oraclejdk8:slim AS zu
VOLUME /tmp
ADD target/zu-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar",  "/app.jar" ]
