package com.mnit.tnt

import com.mnit.tnt.domain.relation.Offer
import com.mnit.tnt.domain.node.Tool
import com.mnit.tnt.domain.node.User
import com.mnit.tnt.domain.relation.Owner
import com.mnit.tnt.repository.ToolRepository
import com.mnit.tnt.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.neo4j.template.Neo4jTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by lihe on 16-12-16.
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RelationModelTest extends Specification {

    @Autowired
    private UserRepository userRepository

    @Autowired
    private ToolRepository toolRepository

    User zhang = new User(userName: 'Zhang')
    User li = new User(userName: 'Li')
    User wang = new User(userName: 'Wang')

    Tool winT500 = new Tool(name: 'T500 windows 10')
    Tool lnxT500 = new Tool(name: 'T500 ubuntu 16')


    def setup() {
        userRepository.save(zhang)
        userRepository.save(li)
        toolRepository.save(winT500)
        toolRepository.save(lnxT500)

        Owner zhangOwnWinT500 = new Owner(user: zhang, tool: winT500)
        winT500.setOwner(zhangOwnWinT500)
        toolRepository.save(winT500)

        Owner zhangOwnLnxT500 = new Owner(user: zhang, tool: lnxT500)
        lnxT500.setOwner(zhangOwnLnxT500)
        toolRepository.save(lnxT500)


        //MATCH (n) DETACH DELETE n

    }

    def cleanup() {
        userRepository.deleteAll();
    }

    def 'user browse tools'() {
        when:
        List<Tool> tools = toolRepository.findAll().toList()

        then:
        tools
        tools.size() == 2
    }

    def 'user/owner list/offer a existing tool' () {
        when:
        Offer zhangOfferWinT500 = new Offer(user: zhang, tool: winT500)

    }

    def 'user/borrower browser listing/offering too'() {
        
    }

    def 'user borrow a tool'() {

    }

    def 'user delivery a tool'() {

    }

    def 'user return a tool'() {

    }

    def 'test it'() {
        expect:
        true
    }
}
