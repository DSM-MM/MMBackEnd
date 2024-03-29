FROM openjdk:11-jre
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/MMProject-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]