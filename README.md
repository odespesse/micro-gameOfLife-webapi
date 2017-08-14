# microGameOfLifeWebApi

## What is it ?
A REST API for the Game of Life webservice.
Basically it is a bridge between HTTP requests and the message queue.

## How to start the microGameOfLifeWebApi application
1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/micro-gameOflife-webapi-2.0.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`

## Use with Docker
1. Go to the `docker/` directory
2. Run the script `build.sh <VERSION>`, replace `<VERSION>` with version number
3. Start the container with `docker run --name mgol-webapi -p 8180:8180 -p 80:80 -d mgol-webapi:<VERSION>`

## Health Check
To see your applications health enter url `http://localhost:8081/healthcheck`

## Stack
- Java
- Dropwizard
- microGameOfLifeMessageQueue
- Maven
