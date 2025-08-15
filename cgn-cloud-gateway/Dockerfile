# Dockerfile for cgn-cloud-gateway service

# =================================================================
# STAGE 1: Build the application using Maven
# =================================================================
FROM maven:3.8-eclipse-temurin-17 AS builder

# Set the working directory in the build image
WORKDIR /app

# --- Step 1: Cache Dependencies ---
# Copy all pom.xml files first to leverage Docker layer caching.
# This step is only re-run when a pom.xml file changes.
# Note: We use `../` because the Docker build context is `/cgn-cloud-gateway/`
COPY ../pom.xml ./pom.xml
COPY ../cgn-cloud-api/pom.xml ./cgn-cloud-api/pom.xml
COPY ../cgn-cloud-data-service/pom.xml ./cgn-cloud-data-service/pom.xml
COPY ../cgn-cloud-framework/pom.xml ./cgn-cloud-framework/pom.xml
COPY ../cgn-cloud-gateway/pom.xml ./cgn-cloud-gateway/pom.xml
COPY ../cgn-cloud-system/pom.xml ./cgn-cloud-system/pom.xml
# Copy the parent pom for the sub-modules
COPY ../cgn-cloud-module/pom.xml ./cgn-cloud-module/pom.xml
# Copy poms for all modules under cgn-cloud-module
COPY ../cgn-cloud-module/cgn-module-iot/pom.xml ./cgn-cloud-module/cgn-module-iot/pom.xml
COPY ../cgn-cloud-module/cgn-module-monitor/pom.xml ./cgn-cloud-module/cgn-module-monitor/pom.xml
COPY ../cgn-cloud-module/cgn-module-quartz/pom.xml ./cgn-cloud-module/cgn-module-quartz/pom.xml

# Download all dependencies based on the poms
RUN mvn dependency:go-offline

# --- Step 2: Copy Source Code ---
# Copy the entire project's source code
COPY ../. .

# --- Step 3: Build only the target module and its dependencies ---
# This is the optimized build command.
# It will build 'cgn-cloud-gateway' and any local modules it depends on (like cgn-cloud-api),
# but will skip other independent services like 'cgn-cloud-system'.
# Please verify the artifactId in your /cgn-cloud-gateway/pom.xml
RUN mvn clean install -pl cgn-cloud-gateway -am -DskipTests=true


# =================================================================
# STAGE 2: Create the final lightweight runtime image
# =================================================================
FROM eclipse-temurin:17-jre

LABEL maintainer="zyan888@88.com"

WORKDIR /app

# --- Step 4: Copy the final JAR from the builder stage ---
# The path inside the builder stage is `/app/<module-name>/target/<jar-file>`
# IMPORTANT: Please verify the final JAR file name. It might contain a version number.
# For example: cgn-cloud-gateway-1.0.0.jar
COPY --from=builder /app/cgn-cloud-gateway/target/cgn-cloud-gateway.jar app.jar

ENV TZ=Asia/Shanghai
EXPOSE 18080

# --- Step 5: Run the application ---
ENTRYPOINT ["java", "-server", "-Xms512M", "-Xmx512M", "-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF-8", "-XX:+HeapDumpOnOutOfMemoryError", "-jar", "app.jar" ]
