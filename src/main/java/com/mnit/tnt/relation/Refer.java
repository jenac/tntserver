package com.mnit.tnt.relation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mnit.tnt.domain.Delivery;
import com.mnit.tnt.domain.Order;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by lihe on 5/21/17.
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type="REFER")
public class Refer {
    @GraphId
    private Long id;

    @StartNode
    private Delivery delivery;

    @EndNode
    private Order order;

    private String memo;


}
