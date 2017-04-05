package com.mnit.tnt

import com.mnit.tnt.domain.relation.Borrow
import com.mnit.tnt.domain.relation.Offer
import com.mnit.tnt.domain.node.Tool
import com.mnit.tnt.domain.node.User
import com.mnit.tnt.domain.relation.Own
import com.mnit.tnt.repository.OfferRepository
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

    User zhang = new User(userName: 'ZhangSan')
    User li = new User(userName: 'LiSi')
    User wang = new User(userName: 'WangWu')
    User zhao = new User(userName: 'ZhaoLiu')

    Tool winT500 = new Tool(name: 'T500 windows 10')
    Tool lnxT500 = new Tool(name: 'T500 ubuntu 16')
    Tool dellD610 = new Tool(name: 'Dell D610 free BSB')

    @Autowired
    RepositoryHelper repositoryHelper

    @Autowired
    OfferRepository offerRepository

    def setup() {
        userRepository.save(zhang)
        userRepository.save(li)
        userRepository.save(wang)
        toolRepository.save(winT500)
        toolRepository.save(lnxT500)

        Own zhangOwnWinT500 = new Own(user: zhang, tool: winT500)
        winT500.setOwner(zhangOwnWinT500)
        toolRepository.save(winT500)

        Own zhangOwnLnxT500 = new Own(user: zhang, tool: lnxT500)
        lnxT500.setOwner(zhangOwnLnxT500)
        toolRepository.save(lnxT500)


        Own wangOwnDellD610 = new Own(user: wang, tool: dellD610)
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

        //zhang list/offer tool for sharing
        when:
        Offer zhangOffferWinT500 = new Offer(user: zhang, tool: winT500, active: true)
        zhang.addOffer(zhangOffferWinT500)
        winT500.addOffer(zhangOffferWinT500)

        userRepository.save(zhang)
        toolRepository.save(winT500)

        //the offer expires as time goes
        Offer readOffer = zhang.getCurrentOffers().first()
        readOffer.setActive(false)
        repositoryHelper.saveOffer(readOffer)
        zhang.removeOffer(readOffer)
        userRepository.save(zhang)

        //zhang list the tool again
        Offer offer2nd = new Offer(user: zhang, tool: winT500, active: true)
        zhang.addOffer(offer2nd)
        userRepository.save(zhang)

        //wang list offer
        Offer wangOffer = new Offer(user: wang, tool: dellD610, active: true)
        repositoryHelper.saveOffer(wangOffer)

        //li check active offers
        List<Offer> offers = offerRepository.getActiveOffers();

        then:
        offers.size() == 2

        //li borrow t500
        when:
        Offer t500Offer = offers.find({ it -> it.getUser().id == zhang.id})

        then:
        t500Offer

        when:
        Borrow borrow = new Borrow()

        then:
        true

    }


}
