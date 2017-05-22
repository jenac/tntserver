package com.mnit.tnt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.relation.BasedOn;
import com.mnit.tnt.relation.Place;
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
public class Order {
    @GraphId
    private Long id;
    private String OrderTerm;
    private Date dateCreated;

    @Relationship(type="BASEDON", direction = Relationship.OUTGOING)
    private BasedOn basedOn;

    @Relationship(type="PLACE", direction = Relationship.INCOMING)
    private Place place;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderTerm() {
        return OrderTerm;
    }

    public void setOrderTerm(String orderTerm) {
        OrderTerm = orderTerm;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BasedOn getBasedOn() {
        return basedOn;
    }

    public void setBasedOn(BasedOn basedOn) {
        this.basedOn = basedOn;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
