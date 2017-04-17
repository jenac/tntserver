package com.mnit.tnt.domain.relation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.tnt.domain.node.Tool;
import com.mnit.tnt.domain.node.User;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Date;

/**
 * Created by lihe on 4/16/17.
 */
//@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "HOLD")
public class Hold {
    @GraphId
    private Long id;

    @StartNode
    private User user;

    @EndNode
    private Tool tool;

    private Date begin;

    private Date end;
}
