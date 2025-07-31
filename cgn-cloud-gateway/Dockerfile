FROM eclipse-temurin:17-jre
LABEL maintainer="zyan888@88.com"

WORKDIR /app


ARG JAR_FILE=./target/cgn-cloud-gateway.jar
COPY ${JAR_FILE} app.jar

ENV TZ=Asia/Shanghai
EXPOSE 18080

ENTRYPOINT ["java", "-server", "-Xms512M", "-Xmx512M", "-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF-8", "-XX:+HeapDumpOnOutOfMemoryError", "-jar", "app.jar" ]
