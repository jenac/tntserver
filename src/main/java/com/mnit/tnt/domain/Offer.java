package com.mnit.tnt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

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
    private String offerType;
    private String toolID;
    private String price;
    private Date startDate;
    private Date endDate;
    private String note;
    private String providerUserID;
    private String consumerUserID;
    private String status;
    private Date dateCreated;
    private Date dateUpdated;


    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getToolID() {
        return toolID;
    }

    public void setToolID(String toolID) {
        this.toolID = toolID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getProviderUserID() {
        return providerUserID;
    }

    public void setProviderUserID(String providerUserID) {
        this.providerUserID = providerUserID;
    }

    public String getConsumerUserID() {
        return consumerUserID;
    }

    public void setConsumerUserID(String consumerUserID) {this.consumerUserID = consumerUserID;}

    public String getStatus() { return status;}

    public void setStatus(String status){ this.status = status;}

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
