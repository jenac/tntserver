package com.mnit.tnt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.relation.Create;
import com.mnit.tnt.relation.Has;
import com.mnit.tnt.relation.Refer;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.lang.reflect.Type;
import java.util.Date;

//import lombok.Data;

/**
 * Created by Jun on 2/26/17.
 */
@NodeEntity
@JsonIdentityInfo(generator = JSOGGenerator.class)
//@Data
public class Offer {

    @GraphId
    private Long id;
    private String offerNote;
    private Date dateCreated;

    @Relationship(type="REFER", direction = Relationship.INCOMING)
    private Refer refer;

    @Relationship(type="CREATE", direction = Relationship.INCOMING)
    private Create create;

    @Relationship(type="HAS", direction = Relationship.OUTGOING)
    private Has has;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfferNote() {
        return offerNote;
    }

    public void setOfferNote(String offerNote) {
        this.offerNote = offerNote;
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

    public Create getCreate() {
        return create;
    }

    public void setCreate(Create create) {
        this.create = create;
    }

    public Has getHas() {
        return has;
    }

    public void setHas(Has has) {
        this.has = has;
    }
}
