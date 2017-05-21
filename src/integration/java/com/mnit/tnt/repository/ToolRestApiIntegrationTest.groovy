package com.mnit.tnt.repository
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import org.springframework.http.HttpStatus
import spock.lang.Ignore
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON
import spock.lang.Shared
/**
 * Created by jing on 3/1/17.
 */
@Ignore
class ToolRestApiIntegrationTest extends Specification {
    @Shared
    RESTClient restClient

    def setup() {
        restClient = new RESTClient('http://localhost:8080')
    }

    def 'get tool list from /tool'() {
        when:
        def response = restClient.get(path: '/tool')

        then:
        response.status == HttpStatus.OK.value()

        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        String responseText = response.data.text
        responseText
        def json = new JsonSlurper().parseText(responseText)
        json._links.self.href == "http://localhost:8080/tool"
    }

    def 'post to /tool creates new tool' () {
        //create: post
        when:
        def response = restClient.post(path: '/tool',
                body: [toolName     : 'some-tool',
                       description  : 'description',
                       imageUrl     : 'imageUrl',
                       valid        : true,
                       dateCreated  : null,
                       dateUpdated  : null],
                requestContentType: JSON)

        then:
        response.status == HttpStatus.CREATED.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        String location = response.headers.'Location'.toString()
        location.startsWith('http://localhost:8080/tool/')
    }

    def 'get to /tool/id read tool'() {
        given:
        String location = createToolForTest()
        //read: get
        when:
        def response = restClient.get(path: location)

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        def getJson = parseResponseJson(response)
        getJson.toolName == 'some-tool'
        getJson.description == 'description'
        getJson.imageUrl == 'imageUrl'
        getJson.valid == true
        getJson.dateCreated == null
        getJson.dateUpdated == null
    }

    def 'put to /tool/id for full update tool'() {
        given:
        String location = createToolForTest()

        //update: put, put is idempotent only for full update
        when:
        def response = restClient.put(path: location,
                body: [toolName     : 'put-some-tool',
                       description  : 'put-description',
                       imageUrl     : 'put-imageUrl',
                       valid        : false,
                       dateCreated  : null,
                       dateUpdated  : null],
                requestContentType: JSON)

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        response.headers.'Location'.toString() == location
        def putJson = parseResponseJson(response)
        putJson.toolName == "put-some-tool"
        putJson.description == "put-description"
        putJson.imageUrl == "put-imageUrl"
        putJson.valid == false
        putJson.dateCreated == null
        putJson.dateUpdated == null
        putJson._links.self.href == location
        putJson._links.tool.href == location
    }

    def 'patch to /tool/id for partial update'() {
        given:
        String location = createToolForTest()

        //update partially: patch
        when:
        def response = restClient.patch(path: location,
                body: [toolName: 'patch-some-tool',
                       description: 'patch-description'],
                requestContentType: JSON)

        then:
        //why 200 not 204 with no content?
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        def postJson = parseResponseJson(response)
        postJson.toolName == "patch-some-tool"
        postJson.imageUrl == "imageUrl"
        postJson.valid == true
        postJson.dateCreated == null
        postJson.dateUpdated == null
        postJson._links.self.href == location
        postJson._links.tool.href == location
    }

    def 'delete to /tool/id to delete tool'() {
        given:
        String location = createToolForTest()
        //delete: delete
        when:
        def response = restClient.delete(path: location)

        then:
        response.status == HttpStatus.NO_CONTENT.value()
    }

    //post to create tool and returns location
    String createToolForTest() {
        def response = restClient.post(path: '/tool',
                body: [toolName     : 'some-tool',
                       description  : 'description',
                       imageUrl     : 'imageUrl',
                       valid        : true,
                       dateCreated  : null,
                       dateUpdated  : null],
                requestContentType: JSON)

        if (response.status == HttpStatus.CREATED.value()) {
            return response.headers.'Location'.toString()
        }
        else {
            throw new Exception("failed to create tool")
        }
    }

    Map parseResponseJson(response) {
        String responseText = response.data.text
        new JsonSlurper().parseText(responseText)
    }
}
