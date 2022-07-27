FROM openjdk:11
EXPOSE 8081
ADD target/docker-cid-cd.jar docker-cid-cd.jar
ENTRYPOINT ["java", "-jar", "/docker-cid-cd.jar"]