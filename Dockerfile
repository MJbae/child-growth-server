FROM openjdk:11
ARG PROJECT_NAME="childGrowth"
ARG JAR_PATH=./build/libs/*.jar
COPY ${JAR_PATH} ${PROJECT_NAME}.jar

ENTRYPOINT ["java", "-jar", "childGrowth.jar"]
