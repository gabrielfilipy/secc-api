FROM openjdk:11-jre

WORKDIR /app

COPY target/*.jar /app/secc.jar

EXPOSE 1111

CMD ["java", "-jar", "secc.jar"]
