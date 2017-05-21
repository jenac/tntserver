package com.mnit.tnt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

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
}
