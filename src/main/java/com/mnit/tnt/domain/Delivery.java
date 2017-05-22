package com.mnit.tnt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.relation.Fulfill;
import com.mnit.tnt.relation.Refer;
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
public class Delivery {
    @GraphId
    private Long id;
    private String deliveryProof;
    private Date dateCreated;

    @Relationship(type = "REFER", direction = Relationship.OUTGOING)
    private Refer refer;

    @Relationship(type = "FULFILL", direction = Relationship.INCOMING)
    private Fulfill fulfill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryProof() {
        return deliveryProof;
    }

    public void setDeliveryProof(String deliveryProof) {
        this.deliveryProof = deliveryProof;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Refer getRefer() {
        return refer;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }

    public Fulfill getFulfill() {
        return fulfill;
    }

    public void setFulfill(Fulfill fulfill) {
        this.fulfill = fulfill;
    }
}
