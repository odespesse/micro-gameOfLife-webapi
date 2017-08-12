# DESCRIPTION:    Create the microGameOfLife API webapp in a container
# AUTHOR:         Olivier Despesse <olived19@gmail.com>
# COMMENTS:
#   This file describes how to build the microGameOfLife API webapp
#   in a container with all dependencies installed.
# USAGE:
#   # Build image
#   docker build -t mgol-webapi:<VERSION> .
#
#   # Run service
#   docker run --name mgol-webapi -p 8180:8180 -p 80:80 -d mgol-webapi:<VERSION>
#

# Base docker image
FROM openjdk:8u131-jre-alpine
MAINTAINER Olivier Despesse <olived19@gmail.com>

EXPOSE 80 8081
COPY micro-gameoflife-webapi-<VERSION>.jar /opt/webapi/
COPY config.yml /opt/webapi/
WORKDIR /opt/webapi

ENTRYPOINT ["java", "-jar", "micro-gameoflife-webapi-<VERSION>.jar", "server"]
CMD ["./config.yml"]
