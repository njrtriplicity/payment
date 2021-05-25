FROM openjdk:8-jdk-alpine
MAINTAINER nehalalwaniblr@gmail.com
COPY /target/payment-0.0.1-SNAPSHOT.jar payment.jar
ENTRYPOINT ["java","-jar","/payment.jar"]