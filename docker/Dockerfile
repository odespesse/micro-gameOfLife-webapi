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
#   docker run --name mgol-webapi:<VERSION> -p 8081:8081 -d mgol-webapi
#

# Base docker image
FROM openjdk:8
MAINTAINER Olivier Despesse <olived19@gmail.com>

EXPOSE 80 8081
COPY microGameOfLifeWebApi-<VERSION>.jar /opt/webapi/
COPY config.yml /opt/webapi/
WORKDIR /opt/webapi

ENTRYPOINT ["java", "-jar", "microGameOfLifeWebApi-<VERSION>.jar", "server"]
CMD ["./config.yml"]
