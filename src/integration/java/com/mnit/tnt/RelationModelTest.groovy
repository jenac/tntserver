package com.mnit.tnt

import com.mnit.tnt.domain.relation.Borrow
import com.mnit.tnt.domain.node.Tool
import com.mnit.tnt.domain.node.User
import com.mnit.tnt.domain.relation.Hold
import com.mnit.tnt.domain.relation.Own
import com.mnit.tnt.domain.relation.Status
import com.mnit.tnt.repository.BorrowRepository
import com.mnit.tnt.repository.HoldRepository
import com.mnit.tnt.repository.OwnRepository

import com.mnit.tnt.repository.ToolRepository
import com.mnit.tnt.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

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
    Tool hpElite = new Tool(name: 'HP elite laptop', active: false, price: 0)
    Tool appleMacbook =  new Tool(name: 'Apple Macbook', active: false, price: 0)

    @Autowired
    OwnRepository ownRepository

    @Autowired
    BorrowRepository borrowRepository

    @Autowired
    HoldRepository holdRepository

    def setup() {
        //clean up database first
        userRepository.deleteAll();

        //setup begins here
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

        ownRepository.save(new Own(user: li, tool: appleMacbook, memo: 'li -> macbook'))
        ownRepository.save(new Own(user: zhao, tool: hpElite, memo: 'zhao -> hpElite'))


        Hold zhangHoldWinT500 = new Hold(user: zhang, tool: winT500, since: new Date())
        winT500.setHold(zhangHoldWinT500)
        toolRepository.save(winT500)
        Hold zhangHoldLnxT500 = new Hold(user: zhang, tool: lnxT500, since: new Date())
        lnxT500.setHold(zhangHoldLnxT500)
        toolRepository.save(lnxT500)
        Hold wangHoldD610 = new Hold(user: wang, tool: dellD610, since: new Date())
        dellD610.setHold(wangHoldD610)
        toolRepository.save(dellD610)

        holdRepository.save(new Hold(user: li, tool: appleMacbook, since: new Date()))
        holdRepository.save(new Hold(user: zhao, tool: hpElite, since: new Date()))


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
        tools.size() == 5


        //zhang login
        User zhangLogin = userRepository.findOneByUsernameAndPassword(zhang.username, zhang.password)

        then:
        zhangLogin

        //zhang look at his tools
        when:
        List<Tool> myTools = toolRepository.findByOwnerId(zhangLogin.id);

        then:
        myTools.size() == 2

        //zhang list/share ATool
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
        Borrow liBorrowWinT500 = new Borrow(user: li, tool: winT500, status: Status.ACTIVE, date: new Date())
        borrowRepository.save(liBorrowWinT500)

        //zhao borrow t500
        Borrow zhaoBorrowWinT500 = new Borrow(user: zhao, tool: winT500, status: Status.ACTIVE, date: new Date())
        borrowRepository.save(zhaoBorrowWinT500)

        //zhao borrow t500 linux
        Borrow zhaoBorrowLnxT500 = new Borrow(user: zhao, tool: lnxT500, status: Status.ACTIVE, date: new Date())
        borrowRepository.save(zhaoBorrowLnxT500)

        then:
        true

        //zhang check borrow on ATool
        when:
        List<Borrow> borrows = borrowRepository.getActiveBorrowForOwner(zhangLogin.id)
        borrows.each {
            it ->
                println("${it.user.username} want borrow ${it.tool.name}")
        }

        then:
        borrows.size() == 3

        //zhang accept borrow 0
        //transaction needed
        when:
        Borrow borrow0 =borrows.first()
        borrow0.status = Status.ACCEPTED
        borrowRepository.save(borrow0)
        //reject all active borrow on the tool
        List<Borrow> otherActiveBorrows = borrowRepository.getActiveBorrowOnTool(borrow0.tool.id)
        assert otherActiveBorrows.size() == 0
        otherActiveBorrows.each {
            it ->
                it.status = Status.REJECTED
                borrowRepository.save(it)
        }


        /*
        //change holder, bad practice
        Tool theTool = borrow0.tool //for some reason this tool does not have hold, but after reload it is here.
        // are there a config for lazy/eager for neo4j?
        Tool reloaded = toolRepository.findOne(theTool.id)
        reloaded.hold.user = borrow0.user
        toolRepository.save(reloaded) //this does not work!
        holdRepository.save(reloaded.hold) //this does not work!
        //looks like the start/end node cannot change
        */

        //end old hold
        Tool reloaded = toolRepository.findOne(borrow0.tool.id)
        reloaded.hold.untill = new Date()
        holdRepository.save(reloaded.hold)
        //create new hold relation.
        Hold borrow0HoldTool = new Hold(user: borrow0.user, tool: borrow0.tool, since: new Date())
        holdRepository.save(borrow0HoldTool)
        //deactive tool
        reloaded.active = false
        toolRepository.save(reloaded)

        then:
        true

        //zhang accept borrow 2
        when:
        Borrow borrow2 = borrows.last()
        borrow2.status = Status.ACCEPTED
        borrowRepository.save(borrow2)
        //reject all active borrow on the tool
        otherActiveBorrows = borrowRepository.getActiveBorrowOnTool(borrow2.tool.id)
        assert otherActiveBorrows.size() == 1
        otherActiveBorrows.each {
            it ->
                it.status = Status.REJECTED
                borrowRepository.save(it)
        }

        //end old hold
        reloaded = toolRepository.findOne(borrow2.tool.id)
        reloaded.hold.untill = new Date()
        holdRepository.save(reloaded.hold)
        //create new hold relation.
        Hold borrow2HoldTool = new Hold(user: borrow2.user, tool: borrow2.tool, since: new Date())
        holdRepository.save(borrow2HoldTool)
        //deactive tool
        reloaded.active = false
        toolRepository.save(reloaded)

       then:
        true

       //borrower check current holding borrowed tools.
        when:
        User borrower = borrow0.user
        List<Tool> myBorrowedTools = toolRepository.getBorrowingToolsByUserId(borrower.id)

        then:
        myBorrowedTools.size() == 1

       //borrower return tool, once work finished
        when:
        Tool selectedToReturn = myBorrowedTools.first() //this one does not have hold
        Tool reloadedSelectedToReturn = toolRepository.findOne(selectedToReturn.id)
        //end old hold
        reloadedSelectedToReturn.hold.untill = new Date()
        holdRepository.save(reloadedSelectedToReturn.hold)
        holdRepository.save(new Hold(user: reloadedSelectedToReturn.own.user, tool: reloadedSelectedToReturn, since: new Date()))


        then:
        true




    }


}
