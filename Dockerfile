FROM maven:latest
#set maven dependency to specific version?

WORKDIR /code

COPY . /code

ARG HOST
ARG PASSWORD
ARG USER
ARG DATABASE

ENV PASSWORD ${PASSWORD}
ENV USER ${USER}
ENV HOST ${HOST}
ENV DATABASE ${DATABASE}

RUN mvn clean install package -DskipTests=true

EXPOSE 8080

CMD ["java","-jar", "target/ea-agile-sprint-api-1.0-SNAPSHOT.jar", "server", "config.yml"]