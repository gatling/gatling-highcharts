#!/bin/bash

echo "realm=Sonatype Nexus Repository Manager" >> ~/.sbt/.credentials
echo "url=oss.sonatype.org" >> ~/.sbt/.credentials
echo "user=$SONATYPE_USERNAME" >> ~/.sbt/.credentials
echo "password=$SONATYPE_PASSWORD" >> ~/.sbt/.credentials