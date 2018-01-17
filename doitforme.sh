#!/bin/bash

add1=linuxetu.univ-lyon1.fr
add2=192.168.76.146
id1=p1206976
id2=ubuntu
key=pedabdcloud

mvn assembly:assembly
scp -i ~/cles/$key target/stormTP-0.1-jar-with-dependencies.jar $id1@$add1:stormTP-1.1-jar-with-dependencies.jar
