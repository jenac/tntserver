package com.mnit.tnt.repository;

import com.mnit.tnt.domain.relation.Offer;
import com.mnit.tnt.domain.relation.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by lihe on 4/1/17.
 */
@Component
public class RepositoryHelper {
    @Autowired
    Neo4jTemplate template;

    public void saveOffer(Offer offer) {
        template.save(offer);
    }

    public void saveOwner(Owner owner) { template.save(owner); }
}
