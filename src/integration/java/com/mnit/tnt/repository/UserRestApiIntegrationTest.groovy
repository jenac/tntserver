package com.mnit.tnt.repository

import groovyx.net.http.RESTClient
import org.springframework.http.HttpStatus
import spock.lang.Ignore

import static groovyx.net.http.ContentType.JSON
import spock.lang.Shared
import spock.lang.Specification


/**
 * Created by lihe on 16-12-16.
 */
@Ignore
class UserRestApiIntegrationTest extends Specification {
    @Shared
    RESTClient restClient

    def setup() {
        restClient = new RESTClient('http://localhost:8080')
    }


    def 'get user list from /users'() {
        when:
        def response = restClient.get(path: '/users')

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        String s = new String(response.data)

        response.data._embedded
        response.data.users
    }

    def 'create user by posting to /users'() {
        when:
        def response = restClient.post(path: '/users',
                body: [userName: 'some-user',
                       password: 'passw0rd',
                       firstName: 'first',
                       lastName: 'last',
                        email: 'email',
                        stormPathHref: 'href',
                        valid: true,
                        dateCreated: null,
                        dateUpdated: null], requestContentType: JSON)

        then:
        response.status == HttpStatus.OK.value()
        response.data.status == 'UP'
    }
}