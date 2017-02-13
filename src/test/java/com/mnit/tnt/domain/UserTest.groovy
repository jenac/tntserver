package com.mnit.tnt.domain

import spock.lang.Specification

/**
 * Created by lihe on 2/12/17.
 */
class UserTest extends Specification {

    def 'user has getter and setter'() {
        given:
        User u = new User()

        when:
        u.userName = "hello"

        then:
        u.getUserName() == "hello"
    }
}
