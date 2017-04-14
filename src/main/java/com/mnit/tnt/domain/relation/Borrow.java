package com.mnit.tnt.domain.relation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.tnt.domain.node.Tool;
import com.mnit.tnt.domain.node.User;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lihe on 4/2/17.
 */
//@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")

@RelationshipEntity(type = "BORROW")
public class Borrow {
    @GraphId
    private Long id;

    @StartNode
    private User user;

    @EndNode
    private Tool tool;

    private BigDecimal price;

    private String term;

    private Boolean active;

    private Date date;


}
