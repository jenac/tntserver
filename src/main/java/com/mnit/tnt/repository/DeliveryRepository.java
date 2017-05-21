package com.mnit.tnt.repository;

import com.mnit.tnt.domain.Delivery;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by lihe on 5/21/17.
 */
@RepositoryRestResource(collectionResourceRel = "offer", path = "offer")
public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, Long> {

}
