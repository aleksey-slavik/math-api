FROM openjdk:8
VOLUME /tmp
ADD /build/libs/math-0.0.1.jar math-api.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar /math-api.jar"]