#!/bin/sh

title1="Consumer"
title2="Producer"

cmd1="java -cp target/Kafka-jar-with-dependencies.jar Part1.Consumer parallelFinalPart1 group1"
cmd2="java -cp target/Kafka-jar-with-dependencies.jar Part1.Producer parallelFinalPart1"

gnome-terminal --tab --title="$title1" --command="bash -c '$cmd1; $SHELL'"
gnome-terminal --tab --title="$title2" --command="bash -c '$cmd2; $SHELL'"