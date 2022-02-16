#!/bin/bash -ex

WD=$(dirname $0)

$WD/build.sh

url=https://artifactory-publish.prod.dowjones.io/artifactory/djsp-pubedit-release-local

for artifact in xalan serializer; do
	mvn deploy:deploy-file -DrepositoryId=artifactory -Durl=$url -Dpackaging=jar -DgroupId=com.dowjones.xalan -DartifactId=$artifact -Dversion=2.7.2.1 -Dfile=$WD/build/$artifact.jar
done
