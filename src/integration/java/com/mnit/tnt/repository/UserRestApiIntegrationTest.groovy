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

    /*
    @Before
    public void deleteAllBeforeTests() throws Exception {
        personRepository.deleteAll();
    }*/

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

    def 'crud user by post/get/put/delete to /user'() {
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
/*
        //update partially: post
        when:
        response = restClient.post(path: location,
                body: [userName     : 'post-some-user',
                       password     : 'post-passw0rd'], requestContentType: JSON)

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        response.headers.'Location'.toString() == location
        def postJson = parseResponseJson(response)
        postJson.userName == "post-some-user"
        postJson.password == "post-passw0rd"
        postJson.firstName == "put-first"
        postJson.lastName == "put-last"
        postJson.email == "put-email"
        postJson.stormPathHref == "put-href"
        postJson.valid == false
        postJson.dateCreated == null
        postJson.dateUpdated == null
        postJson._links.self.href == location
        postJson._links.user.href == location
*/
    }

    Map parseResponseJson(response) {
        String responseText = response.data.text
        new JsonSlurper().parseText(responseText)
    }
}

/*


@Test
public void shouldUpdateEntity() throws Exception {

    MvcResult mvcResult = mockMvc.perform(post("/people").content(
            "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
            status().isCreated()).andReturn();

    String location = mvcResult.getResponse().getHeader("Location");

    mockMvc.perform(put(location).content(
            "{\"firstName\": \"Bilbo\", \"lastName\":\"Baggins\"}")).andExpect(
            status().isNoContent());

    mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
            jsonPath("$.firstName").value("Bilbo")).andExpect(
            jsonPath("$.lastName").value("Baggins"));
}

@Test
public void shouldPartiallyUpdateEntity() throws Exception {

    MvcResult mvcResult = mockMvc.perform(post("/people").content(
            "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
            status().isCreated()).andReturn();

    String location = mvcResult.getResponse().getHeader("Location");

    mockMvc.perform(
            patch(location).content("{\"firstName\": \"Bilbo Jr.\"}")).andExpect(
            status().isNoContent());

    mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
            jsonPath("$.firstName").value("Bilbo Jr.")).andExpect(
            jsonPath("$.lastName").value("Baggins"));
}

@Test
public void shouldDeleteEntity() throws Exception {

    MvcResult mvcResult = mockMvc.perform(post("/people").content(
            "{ \"firstName\": \"Bilbo\", \"lastName\":\"Baggins\"}")).andExpect(
            status().isCreated()).andReturn();

    String location = mvcResult.getResponse().getHeader("Location");
    mockMvc.perform(delete(location)).andExpect(status().isNoContent());

    mockMvc.perform(get(location)).andExpect(status().isNotFound());
}

}*/