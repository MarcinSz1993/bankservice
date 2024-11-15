FROM openjdk:21

COPY target/bankservice-0.0.1-SNAPSHOT.jar /usr/app/

COPY src/main/resources/db/changelog /usr/app/src/main/resources/db/changelog

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "bankservice-0.0.1-SNAPSHOT.jar"]