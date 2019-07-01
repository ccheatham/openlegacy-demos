FROM openjdk:alpine
COPY './target/docker-mf-rpc-api.jar' /usr/local
RUN chmod +x ./usr/local/docker-mf-rpc-api.jar
CMD ["java", "-jar", "-Dspring.profiles.active=docker,elk-logs", "./usr/local/docker-mf-rpc-api.jar"]
