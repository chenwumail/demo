FROM frolvlad/alpine-oraclejdk8:slim AS ec
VOLUME /tmp
ADD target/ec-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-Dserver.port=8182", "-jar",  "/app.jar" ]
