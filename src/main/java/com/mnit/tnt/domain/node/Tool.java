package com.mnit.tnt.domain.node;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.domain.relation.Offer;
import com.mnit.tnt.domain.relation.Own;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jing on 3/1/17.
 */

@NodeEntity
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Tool {
    @GraphId
    private Long id;
    private String name;
    private String description;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Relationship(type = "OWN", direction = Relationship.INCOMING)
    private Own owner;

    public Own getOwner() {
        return owner;
    }

    public void setOwner(Own owner) {
        this.owner = owner;
    }

    @Relationship(type = "OFFER")
    private List<Offer> offers = new ArrayList<>();

    void addOffer(Offer offer) {
        offers.add(offer);
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
