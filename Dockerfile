FROM maven:3.5-jdk-8 as maven
RUN mkdir --parents /usr/src/app
WORKDIR /usr/src/app

ADD ./app/pom.xml /usr/src/app/
RUN mvn verify clean --fail-never

ADD ./app /usr/src/app
RUN mvn -Dmaven.test.skip=true install

FROM java:8
COPY --from=maven /usr/src/app/target/*.war /opt/app.war

RUN mkdir upload-dir
