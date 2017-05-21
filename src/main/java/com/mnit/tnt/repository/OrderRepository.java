package com.mnit.tnt.repository;

import com.mnit.tnt.domain.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by lihe on 5/21/17.
 */
@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

}