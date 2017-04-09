package com.mnit.tnt.domain.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.domain.relation.Deliver;
import com.mnit.tnt.domain.relation.Return;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import groovy.transform.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by lihe on 2/11/17.
 */
@NodeEntity
@JsonIdentityInfo(generator = JSOGGenerator.class)
@EqualsAndHashCode
public class User {

    @GraphId
    private Long id;
    private String username;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Relationship(type = "DELIVER", direction = Relationship.UNDIRECTED)
    private Deliver deliver;

    @Relationship(type = "RETURN", direction = Relationship.UNDIRECTED)
    private Return aReturn;

    public Deliver getDeliver() {
        return deliver;
    }

    public void setDeliver(Deliver deliver) {
        this.deliver = deliver;
    }

    public Return getaReturn() {
        return aReturn;
    }

    public void setaReturn(Return aReturn) {
        this.aReturn = aReturn;
    }
}
