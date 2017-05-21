package com.mnit.tnt.repository

import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import org.springframework.http.HttpStatus
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON

/**
 * Created by junwang on 03-05-17.
 */
@Ignore
class OfferRestApiIntegrationTest extends Specification {
    @Shared
    RESTClient restClient

    def setup() {
        restClient = new RESTClient('http://localhost:8080')
    }

    def 'get offer list from /offer'() {
        when:
        def response = restClient.get(path: '/offer')

        then:
        response.status == HttpStatus.OK.value()

        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        String responseText = response.data.text
        responseText
        def json = new JsonSlurper().parseText(responseText)
        json._links.self.href == "http://localhost:8080/offer"
    }
    def 'post to /offer to create new offer' () {
        //create: post
        when:
        def response = restClient.post(path: '/offer',
                body: [offerType     : 'Rent',
                       toolID        : '1',
                       price        : '12.00',
                       startDate     : null,
                       endDate        : null,
                       note         : 'Some note',
                       providerUserID: '1',
                       consumerUserID:  '1212',
                       status        : 'New',
                       dateCreated  : null,
                       dateUpdated  : null], requestContentType: JSON)

        then:
        response.status == HttpStatus.CREATED.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        String location = response.headers.'Location'.toString()
        location.startsWith('http://localhost:8080/offer/')
    }

    def 'get to /offer/id read offer'() {
        given:
        String location = createOfferForTest()
        //read: get
        when:
        def response = restClient.get(path: location)

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        def getJson = parseResponseJson(response)
        getJson.offerType == 'Rent'
        getJson.toolID == '1'
        getJson.price == '12.00'
        getJson.startDate == null
        getJson.endDate == null
        getJson.note == 'Some note'
        getJson.providerUserID == '1'
        getJson.consumerUserID =='1212'
        getJson.status == 'New'
        getJson.dateCreated == null
        getJson.dateUpdated == null
    }

    def 'put to /offer/id for full update offer'() {
        given:
        String location = createOfferForTest()

        //update: put, put is idempotent only for full update
        when:
        def response = restClient.put(path: location,
                body: [offerType     : 'put-Rent',
                       toolID        : 'put-1',
                       price        : 'put-12.00',
                       startDate     : null,
                       endDate        : null,
                       note         : 'put-Some note',
                       providerUserID: 'put-1',
                       consumerUserID:  'put-1212',
                       status        : 'put-New',
                       dateCreated  : null,
                       dateUpdated  : null], requestContentType: JSON)

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        response.headers.'Location'.toString() == location
        def putJson = parseResponseJson(response)
        putJson.offerType == 'put-Rent'
        putJson.toolID == 'put-1'
        putJson.price == 'put-12.00'
        putJson.startDate == null
        putJson.endDate == null
        putJson.note == 'put-Some note'
        putJson.providerUserID == 'put-1'
        putJson.consumerUserID =='put-1212'
        putJson.status == 'put-New'
        putJson.dateCreated == null
        putJson.dateUpdated == null
    }

    def 'patch to /offer/id for partial update'() {
        given:
        String location = createOfferForTest()

        //update partially: patch
        when:
        def response = restClient.patch(path: location,
                body: [offerType: 'patch-Rent',
                       toolID: 'patch-1'], requestContentType: JSON)

        then:
        //why 200 not 204 with no content?
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        def postJson = parseResponseJson(response)
        postJson.offerType == 'patch-Rent'
        postJson.toolID == 'patch-1'
        postJson.price == '12.00'
        postJson.startDate == null
        postJson.endDate == null
        postJson.note == 'Some note'
        postJson.providerUserID == '1'
        postJson.consumerUserID =='1212'
        postJson.status == 'New'
        postJson.dateCreated == null
        postJson.dateUpdated == null
    }

    def 'delete to /offer/id to delete offer'() {
        given:
        String location = createOfferForTest()
        //delete: delete
        when:
        def response = restClient.delete(path: location)

        then:
        response.status == HttpStatus.NO_CONTENT.value()
    }


    String createOfferForTest() {
        def response = restClient.post(path: '/offer',
                body: [offerType     : 'Rent',
                       toolID        : '1',
                       price        : '12.00',
                       startDate     : null,
                       endDate        : null,
                       note         : 'Some note',
                       providerUserID: '1',
                       consumerUserID:  '1212',
                       status        : 'New',
                       dateCreated  : null,
                       dateUpdated  : null], requestContentType: JSON)

        if (response.status == HttpStatus.CREATED.value()) {
            return response.headers.'Location'.toString()
        }
        else {
            throw new Exception("failed to create offer")
        }
    }

    Map parseResponseJson(response) {
        String responseText = response.data.text
        new JsonSlurper().parseText(responseText)
    }
}

