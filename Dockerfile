FROM mariadb

ENV MARIADB_ALLOW_EMPTY_ROOT_PASSWORD=yes

ENV MARIADB_DATABASE=nominas
ENV MARIADB_USER=usuario
ENV MARIADB_PASSWORD=usuario

COPY nominas.sql /docker-entrypoint-initdb.d
