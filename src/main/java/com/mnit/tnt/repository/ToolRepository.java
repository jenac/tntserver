package com.mnit.tnt.repository;
import com.mnit.tnt.domain.node.Tool;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by jing on 3/1/17.
 */

@RepositoryRestResource(collectionResourceRel = "tool", path = "tool")
public interface ToolRepository extends PagingAndSortingRepository<Tool, Long>{

}

