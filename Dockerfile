FROM maven:3.6.3-openjdk-17
ADD . /
RUN mvn clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/target/coffeetime-0.0.1-SNAPSHOT.jar"]
