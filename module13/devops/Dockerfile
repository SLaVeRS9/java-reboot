FROM lippert/adoptopenjdk-17
ARG JAR_FILE=module13-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} /opt/app
ENTRYPOINT ["java","-jar","module13-0.0.1-SNAPSHOT.jar"]