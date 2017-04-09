package com.mnit.tnt.repository;

import com.mnit.tnt.domain.relation.Own;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by lihe on 17-4-8.
 */
public interface OwnRepository extends GraphRepository<Own> {
}
