package com.mnit.tnt.domain.node;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.tnt.domain.relation.Borrow;
import com.mnit.tnt.domain.relation.Own;
//import lombok.Data;
import groovy.transform.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.math.BigDecimal;

/**
 * Created by jing on 3/1/17.
 */

@NodeEntity
@EqualsAndHashCode
//@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
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
    private Own own;

    @Relationship(type = "BORROW", direction = Relationship.INCOMING)
    private Borrow borrow;

    private User holder;

    public Own getOwn() {
        return own;
    }

    public void setOwn(Own own) {
        this.own = own;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
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
