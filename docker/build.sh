#!/usr/bin/env bash

VERSION=$1

cp -p "../target/microGameOfLifeWebApi-${VERSION}.jar" .
cp -p ../config.yml .
sed -i "s/<VERSION>/${VERSION}/g" Dockerfile
docker build -t "mgol-webapi:${VERSION}" .
sed -i "s/${VERSION}/<VERSION>/g" Dockerfile
rm "microGameOfLifeWebApi-${VERSION}.jar"
rm config.yml
