FROM eclipse-temurin:17
LABEL maintainer="mdomingu@mail.com"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} dan-usuarios.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/dan-usuarios.jar"]

