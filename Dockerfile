FROM openjdk:11
EXPOSE 8080
EXPOSE 5432
ADD build/libs/personal-manager-app-0.0.1-SNAPSHOT.jar personal-manager-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/personal-manager-app-0.0.1-SNAPSHOT.jar"]