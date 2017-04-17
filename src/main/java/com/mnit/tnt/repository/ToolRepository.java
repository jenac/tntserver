package com.mnit.tnt.repository;
import com.mnit.tnt.domain.node.Tool;
import com.mnit.tnt.domain.node.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by jing on 3/1/17.
 */

@RepositoryRestResource(collectionResourceRel = "tool", path = "tool")
public interface ToolRepository extends PagingAndSortingRepository<Tool, Long>{
    List<Tool> findByActive(Boolean active);
    @Query("MATCH (t:Tool)<-[:OWN]-(u:User) WHERE id(u)={0} RETURN t")
    List<Tool> findByOwnerId(Long id);

    @Query("MATCH (t:Tool)-[:HOLDER]->(u:User) WHERE id(u)={0} RETURN t")
    List<Tool> getBorrowedToolsByUserId(Long id);
    //List<Tool> findByOwner(o)

}

