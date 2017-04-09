package com.mnit.tnt.domain.node;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.domain.relation.Borrow;
import com.mnit.tnt.domain.relation.Own;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//import lombok.Data;
import groovy.transform.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jing on 3/1/17.
 */

@NodeEntity
@EqualsAndHashCode
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Tool {
    @GraphId
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Boolean active;
    private BigDecimal price;

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

    @Relationship(type = "BORROW", direction = Relationship.INCOMING)
    private Borrow borrower;

    private User holder;

    public Own getOwner() {
        return owner;
    }

    public void setOwner(Own owner) {
        this.owner = owner;
    }

    public Borrow getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrow borrower) {
        this.borrower = borrower;
    }

    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
