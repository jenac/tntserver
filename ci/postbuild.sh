#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" == "master" ]; then
    git config --global user.name "Travis CI"
    git config --global user.email "lihe.chen@gmail.com"
    git remote set-url origin https://jenac:$LHMTECH_CI_TOKEN@github.com/tnt-develop-team/tntserver.git
    ./gradlew tagReleaseVersion
    git checkout master
    ./gradlew increaseBuild
    git commit -am "increase build version [ci skip]"
    cat src/main/resources/build.properties
    git push origin master
else
    #this will run on the release tag branch
    ./gradlew stageJarFile
    ls stage/*.jar
    echo "$TRAVIS_BRANCH"
fi