#!/bin/sh
if [ $# -eq 0 ]
then
	mvn clean package
else
	if [ $1 = "-q" ]
	then
		mvn -Dmaven.test.skip=true clean package
	else
		echo "build.sh [-q]"
		exit 0
	fi
fi

rm -rf output
mkdir output
mkdir output/bin
mkdir output/conf

cp target/yecai-service-1.0-SNAPSHOT.jar output/bin

deploy_dir=deploy

if [ -d $deploy_dir ]
then
    cp deploy/control.sh output/bin
    cp deploy/supervise.* output/bin
    cp deploy/start.sh output/bin
    cp deploy/stop.sh output/bin
fi
