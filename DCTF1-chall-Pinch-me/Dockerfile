FROM ubuntu:latest
RUN apt-get update && apt-get install -y make gcc socat

RUN groupadd pilot
RUN useradd pilot --gid pilot

COPY ./app /app
WORKDIR /app

EXPOSE 7480

ENTRYPOINT [ "bash", "/app/startService.sh" ]
