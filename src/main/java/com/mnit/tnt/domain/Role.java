package com.mnit.tnt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.List;

/**
 * Created by lihe on 16-12-19.
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "ACTED_IN")
public class Role {

    @GraphId
    private Long id;

    private List<String> roles;

    @StartNode
    private Person person;

    @EndNode
    private Movie movie;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
