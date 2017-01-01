package com.mnit.tnt

import com.mnit.tnt.domain.Movie
import com.mnit.tnt.domain.Person
import com.mnit.tnt.domain.Role
import com.mnit.tnt.repository.MovieRepository
import com.mnit.tnt.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Neo4JSpike extends Specification {
    @Autowired
    MovieRepository movieRepository

    @Autowired
    PersonRepository personRepository

    def setup() {
        Movie matrix = new Movie();
        matrix.setTitle("The Matrix");
        matrix.setReleased(1999);

        movieRepository.save(matrix);

        Person keanu = new Person();
        keanu.setFirstName("Keanu");
        keanu.setLastName("Reeves");

        personRepository.save(keanu);

        Role neo = new Role();
        neo.setMovie(matrix);
        neo.setPerson(keanu);
        Collection<String> roleNames = new HashSet();
        roleNames.add("Neo");
        neo.setRoles(roleNames);

        List<Role> roles = new ArrayList();
        roles.add(neo);

        matrix.setRoles(roles);

        movieRepository.save(matrix);
    }

    def cleanup() {
        personRepository.deleteAll();
        movieRepository.deleteAll();
    }

    def 'find movie by title'() {
        given:
        String title = "The Matrix"

        when:
        Movie result = movieRepository.findByTitle(title)

        then:
        result
        result.getReleased() == 1999
    }

    def 'find movie by title contains'() {
        given:
        String title = "atri";

        when:
        Collection<Movie> result = movieRepository.findByTitleContaining(title)

        then:
        result
        result.any()
        result.size() == 1
    }

    def 'find person by lastName'() {
        given:
        String lastName = "Reeves"

        when:
        List<Person> result = personRepository.findByLastName(lastName)

        then:
        result
        result.any()
        result.size() == 1
        result.first().getFirstName() == 'Keanu'
    }

    def 'test graph'() {
        when:
        List<Map<String,Object>> graph = movieRepository.graph(5)

        then:
        graph
        graph.size() == 1

        when:
        Map<String,Object> map = graph.get(0)

        then:
        map
        map.size() == 2

        when:
        String[] cast = (String[])map.get("cast");
        String movie = (String)map.get("movie");

        then:
        movie == "The Matrix"
        //why the following is not working:
//        "Keanu Reeves" == cast[0]
    }
}