package com.mnit.tnt.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.relation.Has;
import com.mnit.tnt.relation.Hold;
import com.mnit.tnt.relation.Own;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;

/**
 * Created by jing on 3/1/17.
 */

@NodeEntity
@JsonIdentityInfo(generator = JSOGGenerator.class)
//@Data
public class Tool {
    @GraphId
    private Long id;
    private String toolName;
    private String description;
    private String imageUrl;

    @Relationship(type="HAS", direction = Relationship.INCOMING)
    private Has has;

    @Relationship(type="OWN", direction = Relationship.INCOMING)
    private Own own;

    @Relationship(type="HOLD", direction = Relationship.INCOMING)
    private Hold hold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
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

    public Has getHas() {
        return has;
    }

    public void setHas(Has has) {
        this.has = has;
    }

    public Own getOwn() {
        return own;
    }

    public void setOwn(Own own) {
        this.own = own;
    }

    public Hold getHold() {
        return hold;
    }

    public void setHold(Hold hold) {
        this.hold = hold;
    }
}
