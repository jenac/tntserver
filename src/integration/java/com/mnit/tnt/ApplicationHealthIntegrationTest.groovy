package com.mnit.tnt

import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import org.springframework.http.HttpStatus
import spock.lang.Ignore
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

    @Ignore
    def 'get version from /info'() {
        when:
        def response = restClient.get(path: '/info')

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/vnd.spring-boot.actuator.v1+json;charset=UTF-8'
        String responseText = response.data.text
        responseText
        def json = new JsonSlurper().parseText(responseText)
        json.app.description == 'The backend of ATool sharing'
        json.app.name == 'TnT Server'
        json.app.version.startsWith '1.'
    }

    def 'get heath from /health'() {
        when:
        def response = restClient.get(path: '/health')

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/vnd.spring-boot.actuator.v1+json;charset=UTF-8'
        String responseText = response.data.text
        responseText
        def json = new JsonSlurper().parseText(responseText)
        json.status == 'UP'
    }
}
