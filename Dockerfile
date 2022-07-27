FROM openjdk:11
EXPOSE 8080
ADD target/docker-cid-cd.jar docker-cid-cd.jar
ENTRYPOINT ["java", "-jar", "/docker-cid-cd.jar"]