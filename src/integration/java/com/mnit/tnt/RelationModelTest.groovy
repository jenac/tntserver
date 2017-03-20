package com.mnit.tnt

import com.mnit.tnt.domain.Offer
import com.mnit.tnt.domain.Tool
import com.mnit.tnt.domain.User
import com.mnit.tnt.repository.ToolRepository
import com.mnit.tnt.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import sun.security.pkcs11.Session

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

    def "hello hi"() {
        given:
        Random random = new Random()

        when:
        1000.times {
            String u = "u" + it.toString().padLeft( 4, '0' )
            Date now = new Date()
            User user = new User(
                    userName: u,
                    password: u,
                    firstName: u,
                    lastName: u,
                    valid: true,
                    dateCreated: now,
                    dateUpdated: now
            )

            userRepository.save(user)

            String t = "t" + it.toString().padLeft(4, '0')
            Tool tool = new Tool(
                    toolName: t,
                    description: t,
                    imageUrl:  t,
                    valid:  true,
                    dateCreated:  now,
                    dateUpdated:  now
            )

            toolRepository.save(tool);

            random.nextInt(3).times {
                Offer offer = new Offer(
                        user: user,
                        tool: tool,
                        offerType: (it % 2 == 0) ? 'free' : 'priced',
                        price: random.nextInt(100),
                        startDate: now,
                        endDate: now,
                        note: 'some note',
                        status: (it % 3 == 0) ? 'minor' : 'major',
                        dateCreated: now,
                        dateUpdated: now
                )
                tool.addOffer(offer);
            }
            toolRepository.save(tool);

//            Movie matrix = new Movie("The Matrix", 1999);
//
//            instance.save(matrix);
//
//            Person keanu = new Person("Keanu Reeves");
//
//            personRepository.save(keanu);
//
//            Role neo = new Role(matrix, keanu);
//            neo.addRoleName("Neo");
//
//            matrix.addRole(neo);
//
//            instance.save(matrix);
        }

        then:
        true
    }

    /*
    def setup() {
        1000.times {
            it
        }Movie matrix = new Movie("The Matrix", 1999);

        instance.save(matrix);

        Person keanu = new Person("Keanu Reeves");

        personRepository.save(keanu);

        Role neo = new Role(matrix, keanu);
        neo.addRoleName("Neo");

        matrix.addRole(neo);

        instance.save(matrix);
    }

    @After
    public void tearDown() {
        session.purgeDatabase();
    }

    @Test
    public void testFindByTitle() {

        String title = "The Matrix";
        Movie result = instance.findByTitle(title);
        assertNotNull(result);
        assertEquals(1999, result.getReleased());
    }

    @Test
    public void testFindByTitleContaining() {
        String title = "Matrix";
        Collection<Movie> result = instance.findByTitleContaining(title);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGraph() {
        Collection<Movie> graph = instance.graph(5);

        assertEquals(1, graph.size());

        Movie movie = graph.iterator().next();

        assertEquals(1, movie.getRoles().size());

        assertEquals("The Matrix", movie.getTitle());
        assertEquals("Keanu Reeves", movie.getRoles().iterator().next().getPerson().getName());
    }*/
}
