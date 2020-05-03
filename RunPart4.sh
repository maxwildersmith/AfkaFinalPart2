#!/bin/sh

title1="Producer"
title2="Unmapped Messages"
title3="USA"
title4="India"
title5="Group 2"

cmd1="java -cp target/Kafka-jar-with-dependencies.jar Part2.Producer parallelFinalPart2"
cmd2="java -cp target/Kafka-jar-with-dependencies.jar Part2.Consumer parallelFinalPart2 group1"
cmd3="java -cp target/Kafka-jar-with-dependencies.jar Part2.Consumer parallelFinalPart2 group2"

gnome-terminal --tab --title="$title1" --command="bash -c '$cmd1; $SHELL'"
gnome-terminal --tab --title="$title2" --command="bash -c '$cmd2; $SHELL'"
gnome-terminal --tab --title="$title3" --command="bash -c '$cmd2; $SHELL'"
gnome-terminal --tab --title="$title4" --command="bash -c '$cmd2; $SHELL'"
gnome-terminal --tab --title="$title5" --command="bash -c '$cmd3; $SHELL'"
