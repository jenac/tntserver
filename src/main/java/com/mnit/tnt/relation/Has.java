package com.mnit.tnt.relation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.domain.Offer;
import com.mnit.tnt.domain.Tool;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by lihe on 5/21/17.
 */
//offer - [has] -> tool
@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type="HAS")
public class Has {
    @GraphId
    private Long id;

    @StartNode
    private Offer offer;

    @EndNode
    private Tool tool;

    private String memo;
}
