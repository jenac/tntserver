package com.mnit.tnt

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification


/**
 * Created by lihe on 16-12-16.
 */
class ApplicationHealthIntegrationTest extends Specification {
    @Shared
    RESTClient restClient

    def setup() {
        restClient = new RESTClient('http://localhost:8080')
    }

    def 'get version from /info'() {
        when:
        def response = restClient.get(path: '/info')

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/json;charset=UTF-8'
        response.data.app.version.startsWith '1.'
    }

    def 'get heath from /health'() {
        when:
        def response = restClient.get(path: '/health')

        then:
        response.status == HttpStatus.OK.value()
        response.data.status == 'UP'
        response.data.mongo.status == 'UP'
    }
}