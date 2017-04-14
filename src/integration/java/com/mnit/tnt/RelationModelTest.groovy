package com.mnit.tnt

import com.mnit.tnt.domain.relation.Borrow
import com.mnit.tnt.domain.node.Tool
import com.mnit.tnt.domain.node.User
import com.mnit.tnt.domain.relation.Own
import com.mnit.tnt.repository.BorrowRepository
import com.mnit.tnt.repository.OwnRepository
import com.mnit.tnt.repository.RepositoryHelper
import com.mnit.tnt.repository.ToolRepository
import com.mnit.tnt.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
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

    User zhang = new User(username: 'Zhang 3', password: 'zhang')
    User li = new User(username: 'Li 4')
    User wang = new User(username: 'Wang 5')
    User zhao = new User(username: 'Zhao 6')

    Tool winT500 = new Tool(name: 'T500 windows 10', active: false, price: 0)
    Tool lnxT500 = new Tool(name: 'T500 ubuntu 16', active: false, price: 0)
    Tool dellD610 = new Tool(name: 'Dell D610 free BSD', active: false, price: 0)

    @Autowired
    RepositoryHelper repositoryHelper

    @Autowired
    OwnRepository ownRepository

    @Autowired
    BorrowRepository borrowRepository

    def setup() {
        userRepository.save(zhang)
        userRepository.save(li)
        userRepository.save(wang)
        userRepository.save(zhao)
        toolRepository.save(winT500)
        toolRepository.save(lnxT500)

        Own zhangOwnWinT500 = new Own(user: zhang, tool: winT500, memo: 'zhang -> winT500')
        winT500.setOwn(zhangOwnWinT500)
        toolRepository.save(winT500)

        Own zhangOwnLnxT500 = new Own(user: zhang, tool: lnxT500, memo: 'zhang -> lnxT500')
        lnxT500.setOwn(zhangOwnLnxT500)
        toolRepository.save(lnxT500)


        Own wangOwnDellD610 = new Own(user: wang, tool: dellD610, memo: 'wang -> dellD610')
        ownRepository.save(wangOwnDellD610)
        repositoryHelper.saveOwner(wangOwnDellD610)
        //MATCH (n) DETACH DELETE n

    }

    def cleanup() {
        userRepository.deleteAll();
    }

    def 'workflow demo'() {
        //user browser tools
        when:
        List<Tool> tools = toolRepository.findAll().toList()

        then:
        tools
        tools.size() == 3


        //zhang login
        User zhangLogin = userRepository.findOneByUsernameAndPassword(zhang.username, zhang.password)

        then:
        zhangLogin

        //zhang look at his tools
        when:
        List<Tool> myTools = toolRepository.findByOwnerId(zhangLogin.id);

        then:
        myTools.size() == 2

        //zhang list/share tool
        when:
        myTools.each {
            it ->
                it.setActive(true)
                if (it.name.contains("windows")) {
                    it.setPrice(20)
                }
                toolRepository.save(it)
        }

        then:
        true

        //li check active tools
        List<Tool> activeTools = toolRepository.findByActive(true)

        then:
        activeTools.size() == 2

        //li borrow t500
        when:
        Borrow liBorrowWinT500 = new Borrow(user: li, tool: winT500, active: true)
        borrowRepository.save(liBorrowWinT500)

        //zhao borrow t500
        Borrow zhaoBorrowWinT500 = new Borrow(user: zhao, tool: winT500, active: true)
        borrowRepository.save(zhaoBorrowWinT500)

        //zhao borrow t500 linux
        Borrow zhaoBorrowLnxT500 = new Borrow(user: zhao, tool: lnxT500, active: true)
        borrowRepository.save(zhaoBorrowLnxT500)

        then:
        true

        //zhang check borrow on tool
        when:
        List<Borrow> borrows = borrowRepository.getActiveBorrowForOwner(zhangLogin.id)
        borrows.each {
            it ->
                println("${it.user.username} want borrow ${it.tool.name}")
        }

        then:
        borrows.size() == 3

        //zhang delivery winT500 to li
        when:

        then:
        true



    }


}
