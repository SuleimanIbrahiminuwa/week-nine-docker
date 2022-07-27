FROM openjdk:11
EXPOSE 8080
ADD target/sule-week-ten-docker.jar sule-week-ten-docker.jar
ENTRYPOINT ["java", "-jar", "/sule-week-ten-docker.jar"]