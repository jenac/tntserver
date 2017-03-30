package com.mnit.tnt.domain.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lihe on 2/11/17.
 */
@NodeEntity
@JsonIdentityInfo(generator = JSOGGenerator.class)
//@Data
public class User {

    @GraphId
    private Long id;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    @Relationship(type = "OWN")
//    private List<Tool> tools = new ArrayList<>();
//
//    public List<Tool> getTools() {
//        return tools;
//    }
}
