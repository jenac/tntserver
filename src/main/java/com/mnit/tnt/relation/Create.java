package com.mnit.tnt.relation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.domain.Offer;
import com.mnit.tnt.domain.User;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by lihe on 5/21/17.
 */
//user - [create] -> offer
@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type="CREATE")
public class Create {
    @GraphId
    private Long id;

    @StartNode
    private User user;

    @EndNode
    private Offer offer;

    private String memo;
}
