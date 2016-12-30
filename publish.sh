#! /bin/bash

curl -k https://travis:ci-builder@52.165.30.58:8443/job/tntserver_deploy/buildWithParameters?token=TntServerDeploymentJenkinsToken_fe796c99c76b&VERSION=1.0.2
#curl -k https://$DEPLOY_JENKINS_USER:$DEPLOY_JENKINS_PASS@52.165.30.58:8443/job/tntserver_deploy/buildWithParameters?token=$DEPLOY_JENKINS_TOKEN&VERSION=$TRAVIS_BRANCH
