package com.mnit.tnt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.relation.Against;
import com.mnit.tnt.relation.Make;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;

/**
 * Created by lihe on 5/21/17.
 */
@NodeEntity
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Restitution {
    @GraphId
    private Long id;
    private String returnMemo;
    private Date dateCreated;

    @Relationship(type="MAKE", direction = Relationship.INCOMING)
    private Make make;

    @Relationship(type="AGAINST", direction = Relationship.OUTGOING)
    private Against against;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReturnMemo() {
        return returnMemo;
    }

    public void setReturnMemo(String returnMemo) {
        this.returnMemo = returnMemo;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Against getAgainst() {
        return against;
    }

    public void setAgainst(Against against) {
        this.against = against;
    }
}
