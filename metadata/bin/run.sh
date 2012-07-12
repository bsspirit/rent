#!/bin/bash

s1=$1 
s2=$2

set lib/*.jar
CLASSPATH=$(IFS=:; echo "$*")

java -Xmx2048m -Xms512m -jar rent.jar $s1 $s2
