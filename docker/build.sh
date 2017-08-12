#!/usr/bin/env bash

VERSION=$1

cp -p "../target/micro-gameoflife-webapi-${VERSION}.jar" .
cp -p ../config.yml .
sed -i "s/<VERSION>/${VERSION}/g" Dockerfile
docker build -t "mgol-webapi:${VERSION}" .
sed -i "s/${VERSION}/<VERSION>/g" Dockerfile
rm "micro-gameoflife-webapi-${VERSION}.jar"
rm config.yml
