# ------------------------------
# Stage 1: Build WAR with Gradle
# ------------------------------
FROM gradle:8.10-jdk25 AS builder

WORKDIR /app

# Copy only Gradle configuration first (to cache dependencies)
COPY build.gradle.kts settings.gradle.kts /app/
COPY gradle /app/gradle

# Download dependencies (this layer will be cached)
RUN gradle build --no-daemon || return 0

# Copy all source code
COPY src /app/src

# Build the WAR file
RUN gradle clean war --no-daemon

# ------------------------------
# Stage 2: Run with Tomcat
# ------------------------------
# Use Tomcat 10.1 (supports Jakarta EE 9/10)
FROM tomcat:10.1-jdk25-temurin

# Remove default ROOT app (optional)
RUN rm -rf /usr/local/tomcat/webapps/ROOT*

# Copy WAR from builder stage
COPY --from=builder /app/build/libs/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]