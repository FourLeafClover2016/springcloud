FROM openjdk:8-jdk-alpine
ADD  target/zuul.jar /opt/
ENTRYPOINT ["/bin/sh", "-c", "echo 10.121.198.132  nacos.server.com >> /etc/hosts && java -Djava.security.egd=file:/dev/./urandom -jar /opt/zuul.jar >> /tmp/1.log 2>&1"]
