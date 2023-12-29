FROM openjdk:17

COPY target/bankservice_app.jar /usr/app/

COPY src/main/resources/db/changelog /usr/app/src/main/resources/db/changelog

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "bankservice_app.jar"]