FROM openjdk:13-jdk-alpine
VOLUME /tmp
COPY target/*.jar peoplesplace-cafe-0.0.1-SNAPSHOT.jar
ENV PORT 7575
EXPOSE 7575/tcp
ENTRYPOINT ["java","-jar","/peoplesplace-cafe-0.0.1-SNAPSHOT.jar"]