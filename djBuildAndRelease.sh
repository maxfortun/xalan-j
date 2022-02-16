#!/bin/bash -e

WD=$(dirname $0)

url=$1
if [ -z "$url" ]; then
	echo "Usage: $0 <maven repo>"
	echo " e.g.: $0 https://artifactory.domain.com/artifactory/repo-release-local"
	exit 1
fi

$WD/build.sh




for artifact in xalan serializer; do
	mvn deploy:deploy-file -DrepositoryId=artifactory -Durl=$url -Dpackaging=jar -DgroupId=com.dowjones.xalan -DartifactId=$artifact -Dversion=2.7.2.1 -Dfile=$WD/build/$artifact.jar
done
