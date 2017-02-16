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
        response.status == HttpStatus.CREATED.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        String location =  response.headers.'Location'.toString()
        location.startsWith('http://localhost:8080/user/')

        //read: get
        when:
        response = restClient.get(path: location)

        then:
        response.status == HttpStatus.OK.value()
        response.headers.'Content-Type'.toString() == 'application/hal+json;charset=UTF-8'
        String responseText = response.data.text
        responseText
        def json = new JsonSlurper().parseText(responseText)
        json.userName == 'some-user'
        json.password == 'passw0rd'
        json.firstName == 'first'
        json.lastName == 'last'
        json.email == 'email'
        json.stormPathHref == 'href'
        json.valid == true
        json.dateCreated == null
        json.dateUpdated == null

        //update: put
//        when:
//        response = restClient.put(path: location)
//
//        then:

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