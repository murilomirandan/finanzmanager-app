# In pom.xml, uncomment these lines
# <!-- define the packaging type -->
# <packaging>jar</packaging>
# <finalName>finanzmanager</finalName>
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]