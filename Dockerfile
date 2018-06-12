FROM maven:3.5-jdk-8
COPY ./app /app
WORKDIR /app
RUN mvn -Dmaven.test.skip=true install
#RUN mvn clean spring-boot:run
EXPOSE 6969 8080
