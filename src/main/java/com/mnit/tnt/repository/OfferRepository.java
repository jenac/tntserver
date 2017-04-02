package com.mnit.tnt.repository;

import com.mnit.tnt.domain.relation.Offer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;


/**
 * Created by lihe on 4/2/17.
 */
public interface OfferRepository extends GraphRepository<Offer> {
    @Query("MATCH ()-[r:OFFER]->() WHERE r.active=true RETURN r LIMIT 25")
    Iterable<Offer> getActiveOffers();
}
