FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD  demo1.jar /opt/demo1.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo1.jar"]
