package com.mnit.tnt.domain.relation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.tnt.domain.node.Tool;
import com.mnit.tnt.domain.node.User;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by lihe on 3/25/17.
 */
//@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")

@RelationshipEntity(type = "OWN")
public class Own {
    @GraphId
    private Long id;

    @StartNode
    private User user;

    @EndNode
    private Tool tool;

    private String memo;
}
