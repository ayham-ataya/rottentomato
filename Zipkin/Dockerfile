FROM stakater/java8-alpine:latest
COPY target/Zipkin-Api-0.0.1-SNAPSHOT.jar zipkin-api.jar
ENTRYPOINT [ "java", "-jar", "zipkin-api.jar" ]