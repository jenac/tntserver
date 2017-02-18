package com.mnit.tnt.repository

import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import org.springframework.http.HttpStatus
import spock.lang.Ignore

import static groovyx.net.http.ContentType.JSON
import spock.lang.Shared
import spock.lang.Specification


/**
 * Created by lihe on 16-12-16.
 */
class UserRestApiIntegrationTest extends Specification {
    @Shared
    RESTClient restClient

    def setup() {
        restClient = new RESTClient('http://localhost:8080')
    }

    def 'get user list from /user'() {
        when:
        def response = restClient.get(path: '/user')

        then:
        response.status == HttpStatus.OK.value()

        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        String responseText = response.data.text
        responseText
        def json = new JsonSlurper().parseText(responseText)
        json._links.self.href == "http://localhost:8080/user"
    }

    def 'crud user by post/get/put/patch/delete to /user'() {
        //create: post
        when:
        def response = restClient.post(path: '/user',
                body: [userName     : 'some-user',
                       password     : 'passw0rd',
                       firstName    : 'first',
                       lastName     : 'last',
                       email        : 'email',
                       stormPathHref: 'href',
                       valid        : true,
                       dateCreated  : null,
                       dateUpdated  : null], requestContentType: JSON)

        then:
        response.status == HttpStatus.CREATED.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        String location = response.headers.'Location'.toString()
        location.startsWith('http://localhost:8080/user/')

        //read: get
        when:
        response = restClient.get(path: location)

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        def getJson = parseResponseJson(response)
        getJson.userName == 'some-user'
        getJson.password == 'passw0rd'
        getJson.firstName == 'first'
        getJson.lastName == 'last'
        getJson.email == 'email'
        getJson.stormPathHref == 'href'
        getJson.valid == true
        getJson.dateCreated == null
        getJson.dateUpdated == null

        //update: put, put is idempotent only for full update
        when:
        response = restClient.put(path: location,
                body: [userName     : 'put-some-user',
                       password     : 'put-passw0rd',
                       firstName    : 'put-first',
                       lastName     : 'put-last',
                       email        : 'put-email',
                       stormPathHref: 'put-href',
                       valid        : false,
                       dateCreated  : null,
                       dateUpdated  : null], requestContentType: JSON)

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        response.headers.'Location'.toString() == location
        def putJson = parseResponseJson(response)
        putJson.userName == "put-some-user"
        putJson.password == "put-passw0rd"
        putJson.firstName == "put-first"
        putJson.lastName == "put-last"
        putJson.email == "put-email"
        putJson.stormPathHref == "put-href"
        putJson.valid == false
        putJson.dateCreated == null
        putJson.dateUpdated == null
        putJson._links.self.href == location
        putJson._links.user.href == location

        //update partially: patch
        when:
        response = restClient.patch(path: location,
                body: [userName     : 'patch-some-user',
                       password     : 'patch-passw0rd'], requestContentType: JSON)

        then:
        //why 200 not 204 with no content?
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        def postJson = parseResponseJson(response)
        postJson.userName == "patch-some-user"
        postJson.password == "patch-passw0rd"
        postJson.firstName == "put-first"
        postJson.lastName == "put-last"
        postJson.email == "put-email"
        postJson.stormPathHref == "put-href"
        postJson.valid == false
        postJson.dateCreated == null
        postJson.dateUpdated == null
        postJson._links.self.href == location
        postJson._links.user.href == location

        //delete: delete
        when:
        response = restClient.delete(path: location)

        then:
        response.status == HttpStatus.NO_CONTENT.value()
    }

    Map parseResponseJson(response) {
        String responseText = response.data.text
        new JsonSlurper().parseText(responseText)
    }
}

