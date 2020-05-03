#!/bin/sh

title1="Producer"
title2="0 Offset"
title3="5 Offset"

cmd1="java -cp target/Kafka-jar-with-dependencies.jar Part3.Producer parallelFinalPart3 "
cmd2="java -cp target/Kafka-jar-with-dependencies.jar Part3.Consumer parallelFinalPart3 group1 0"
cmd3="java -cp target/Kafka-jar-with-dependencies.jar Part3.Consumer parallelFinalPart3 group1 5"


gnome-terminal --tab --title="$title1" --command="bash -c '$cmd1; $SHELL'"
gnome-terminal --tab --title="$title2" --command="bash -c '$cmd2; $SHELL'"
gnome-terminal --tab --title="$title3" --command="bash -c '$cmd3; $SHELL'"
