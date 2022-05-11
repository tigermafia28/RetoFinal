FROM mariadb

ENV MARIADB_ALLOW_EMPTY_ROOT_PASSWORD=yes

ENV MARIADB_DATABASE=noticias
ENV MARIADB_USER=usuario
ENV MARIADB_PASSWORD=usuario

COPY noticias.sql /docker-entrypoint-initdb.d
