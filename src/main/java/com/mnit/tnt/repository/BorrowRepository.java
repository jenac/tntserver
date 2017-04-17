package com.mnit.tnt.repository;

import com.mnit.tnt.domain.relation.Borrow;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by lihe on 17-4-8.
 */
public interface BorrowRepository extends PagingAndSortingRepository<Borrow, Long> {


    @Query("MATCH (b:User)-[bo:BORROW]-> (t:Tool)<-[:OWN]-(u:User) WHERE id(u)={0} AND bo.status='ACTIVE' RETURN bo")
    List<Borrow> getActiveBorrowForOwner(Long id);

    @Query("MATCH (:User)-[bo:BORROW]-> (t:Tool) WHERE id(t)={0} AND bo.status='ACTIVE' RETURN bo")
    List<Borrow> getActiveBorrowOnTool(Long id);
}
