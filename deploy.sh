#! /bin/bash
if [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
    git config --global user.name "Travis CI"
    git config --global user.email "lihe.chen@gmail.com"
    git remote set-url origin https://jenac:$GITHUB_API_KEY@github.com/tnt-develop-team/tntserver.git
    if [ "$TRAVIS_BRANCH" == "master" ]; then
        ./gradlew tagReleaseVersion
        ./gradlew increaseBuildNumber
        git commit -am "increase build version [ci skip]"
        cat src/main/resources/build.properties
        git push -u origin master
    else
        ./gradlew stageReleaseFile
        ls stage/*.jar
        echo "$TRAVIS_BRANCH"
    fi
fi