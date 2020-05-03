#!/bin/sh

title1="Zookeeper"
title2="Kafka"
title3="Topic and build"

cmd1="/bin/kafka/bin/zookeeper-server-start.sh /bin/kafka/config/zookeeper.properties"
cmd2="/bin/kafka/bin/kafka-server-start.sh  /bin/kafka/config/server.properties"
cmd3="/bin/kafka/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic parallelFinalPart1"
cmd4="/bin/kafka/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3 --topic parallelFinalPart2"
cmd5="/bin/kafka/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3 --topic parallelFinalPart3"
cmd6="mvn clean compile assembly:single"

gnome-terminal --tab --title="$title1" --command="bash -c '$cmd1; $SHELL'" \
               --tab --title="$title2" --command="bash -c '$cmd2; $SHELL'" \
               --tab --title="$title3" --command="bash -c '$cmd6;$cmd3;$cmd4;$cmd5;exit $SHELL'"
