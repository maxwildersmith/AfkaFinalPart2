#!/bin/sh

title1="Build"
cmd1="mvn clean compile assembly:single"
gnome-terminal --tab --title="$title1" --command="bash -c '$cmd1;exit; $SHELL'"