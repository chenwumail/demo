#!/bin/sh
export KAFKA_BOOTSTRAP_SERVERS=127.0.0.1:9092 
export STORAGE_TYPE=elasticsearch 
export ES_HOSTS=http://localhost:9200 
exec /usr/bin/java -Dserver.port=9411 -Xms256M -Xmx256M -jar /Users/renren/app/zipkin.jar
