FROM openjdk:11
ARG PROJECT_NAME="childGrowth"

COPY build/libs/${PROJECT_NAME}-0.0.1-SNAPSHOT.jar ${PROJECT_NAME}.jar

ENTRYPOINT ["java", "-jar", "childGrowth.jar"]
