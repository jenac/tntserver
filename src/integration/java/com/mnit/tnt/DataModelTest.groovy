package com.mnit.tnt;

import com.mnit.tnt.domain.Restitution
import com.mnit.tnt.domain.Tool
import com.mnit.tnt.domain.User
import com.mnit.tnt.relation.Own;
import com.mnit.tnt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import spock.lang.Specification;

/**
 * Created by lihe on 5/21/17.
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataModelTest extends Specification {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestitutionRepository restitutionRepository;

    @Autowired
    ToolRepository toolRepository;

    @Autowired
    UserRepository userRepository;


    User zhang3 = new User(userName: "zhang3", password: "zhang3", firstName: "3", lastName: "zhang", email: "zhang3@s.com")
    User li4 = new User(userName: "li4", password: "li4", firstName: "4", lastName: "li", email: "li4@s.com")
    User wang5 = new User(userName: "wang5", password: "wang5", firstName: "5", lastName: "wang", email: "wang5@s.com")
    User zhao6 = new User(userName: "zhao6", password: "zhao6", firstName: "6", lastName: "zhao", email: "zhao6@s.com")

    Tool win95 =  new Tool(toolName: 'win95', description: 'win95', imageUrl: 'win95')
    Tool win98 =  new Tool(toolName: 'win98', description: 'win98', imageUrl: 'win98')
    Tool win2k =  new Tool(toolName: 'win2k', description: 'win2k', imageUrl: 'win2k')
    Tool win7 =  new Tool(toolName: 'win7', description: 'win7', imageUrl: 'win7')
    Tool win8 =  new Tool(toolName: 'win8', description: 'win8', imageUrl: 'win8')
    Tool win10 =  new Tool(toolName: 'win10', description: 'win10', imageUrl: 'win10')


    Tool redhat6 = new Tool(toolName: 'rh6', description: 'rh6', imageUrl: 'rh6')
    Tool redhat7 = new Tool(toolName: 'rh7', description: 'rh7', imageUrl: 'rh7')
    Tool redhat8 = new Tool(toolName: 'rh8', description: 'rh8', imageUrl: 'rh8')
    Tool redhat9 = new Tool(toolName: 'rh9', description: 'rh9', imageUrl: 'rh9')

    def 'create/register user'() {
        when:
        userRepository.save(zhang3)
        userRepository.save(li4)
        userRepository.save(wang5)
        userRepository.save(zhao6)

        then:
        true;
    }

    def 'system create tools'() {
        when:
        toolRepository.save(win95)
        toolRepository.save(win98)
        toolRepository.save(win2k)
        toolRepository.save(win7)
        toolRepository.save(win8)
        toolRepository.save(win10)

        then:
        true
    }

    def 'zhang3 create his tools'() {
        given:
        Own o6 = new Own(user: zhang3, tool: redhat6)
        Own o7 = new Own(user: zhang3, tool: redhat7)
        Own o8 = new Own(user: zhang3, tool: redhat8)
        Own o9 = new Own(user: zhang3, tool: redhat9)

        when:
        redhat6.own =o6
        redhat7.own =o7
        redhat8.own =o8
        redhat9.own =o9
        toolRepository.save(redhat6)
        toolRepository.save(redhat7)
        toolRepository.save(redhat8)
        toolRepository.save(redhat9)

        then:
        true
    }
}
