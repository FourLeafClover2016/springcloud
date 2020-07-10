FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN echo "10.121.198.132  nacos.server.com"
ADD  demo1.jar /opt/demo1.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/demo1.jar"]
