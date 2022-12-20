FROM postgres
ENV POSTGRES_USER admin
ENV POSTGRES_PASSWORD admin
ENV POSTGRES_DB demicondb
COPY demicondb.sql /docker-entrypoint-initdb.d/
