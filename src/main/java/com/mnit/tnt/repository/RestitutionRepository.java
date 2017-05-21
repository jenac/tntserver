package com.mnit.tnt.repository;

import com.mnit.tnt.domain.Restitution;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by lihe on 5/21/17.
 */
@RepositoryRestResource(collectionResourceRel = "restitution", path = "restitution")
public interface RestitutionRepository extends PagingAndSortingRepository<Restitution, Long> {
}
