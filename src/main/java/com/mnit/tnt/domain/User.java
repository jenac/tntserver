package com.mnit.tnt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.relation.*;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
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
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    @Relationship(type="OWN", direction = Relationship.OUTGOING)
    private List<Own> ownList;

    @Relationship(type="CREATE", direction = Relationship.OUTGOING)
    private List<Create> createList;

    @Relationship(type="PLACE", direction = Relationship.OUTGOING)
    private List<Place> placeList;

    @Relationship(type="FULFILL", direction = Relationship.OUTGOING)
    private List<Fulfill> fulfillList;

    @Relationship(type="Make", direction = Relationship.OUTGOING)
    private List<Make> makeList;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Own> getOwnList() {
        return ownList;
    }

    public void setOwnList(List<Own> ownList) {
        this.ownList = ownList;
    }

    public List<Create> getCreateList() {
        return createList;
    }

    public void setCreateList(List<Create> createList) {
        this.createList = createList;
    }

    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    public List<Fulfill> getFulfillList() {
        return fulfillList;
    }

    public void setFulfillList(List<Fulfill> fulfillList) {
        this.fulfillList = fulfillList;
    }

    public List<Make> getMakeList() {
        return makeList;
    }

    public void setMakeList(List<Make> makeList) {
        this.makeList = makeList;
    }
}
