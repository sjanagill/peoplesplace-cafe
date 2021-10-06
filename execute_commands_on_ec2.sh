#!/usr/bin/env bash

kill -9 $(lsof -t -i:7575)
echo "Killed process running on port 7575"

java -jar peoplesplace-cafe-0.0.1-SNAPSHOT.jar
echo "Started server using java -jar command"