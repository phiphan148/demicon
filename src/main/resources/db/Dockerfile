FROM postgres
ENV POSTGRES_USER admin
ENV POSTGRES_PASSWORD admin
ENV POSTGRES_DB demicondb
COPY demicondb.sql /docker-entrypoint-initdb.d/

# FROM schwarzit-docker.jfrog.io/ubi8/openjdk-11:1.11 AS builder

# USER root
# RUN mkdir /app
# RUN chown -R 185 /app

# #The user of the parent image which is the default user
# USER 185
# WORKDIR /app

# ARG JAVA_OPTS

# COPY build.gradle gradlew ./
# COPY gradle ./gradle/
# RUN ./gradlew --version
# RUN ./gradlew --info --no-daemon resolveDependencies

# COPY --chown="185" . .
# ARG EXTRA_ARGS
# RUN ./gradlew --info --no-daemon --parallel clean build ${EXTRA_ARGS}



# FROM schwarzit-docker.jfrog.io/ubi8/openjdk-11-runtime

# #The user of the parent image which is the default user
# USER 185
# WORKDIR /app

# COPY --from=builder /app/build/libs/content.jar /app/app.jar

# ENTRYPOINT exec java ${JAVA_OPTS} -jar /app/app.jar
