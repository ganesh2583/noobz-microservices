# docker build . -t movie-catalog-service
# docker run --name="movie-catalog-service" --publish 9080:9080 --detach movie-catalog-service


# Start with a base image containing Java runtime (mine java 8)
FROM openjdk:8u212-jdk-slim

# Add Maintainer Info
LABEL maintainer="ganesh2583@gmail.com"

# Add a volume pointing to /tmp
RUN mkdir -p /app/

# Make port 9080 available to the world outside this container
EXPOSE 9080

# Add the application's jar to the container
ADD build/libs/movies-catalog-service-0.0.1-SNAPSHOT.jar /app/movie-catalog-service.jar

# Run the jar file 
ENTRYPOINT ["java", "-jar", "/app/movie-catalog-service.jar"]