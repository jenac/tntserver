package com.mnit.tnt.domain.relation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.domain.node.Tool;
import com.mnit.tnt.domain.node.User;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

//import lombok.Data;

/**
 * Created by Jun on 2/26/17.
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "OFFER")
public class Offer {

    @GraphId
    private Long id;

    @StartNode
    private User user;

    @EndNode
    private Tool tool;

    }
