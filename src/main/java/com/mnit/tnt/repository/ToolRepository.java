package com.mnit.tnt.repository;
import com.mnit.tnt.domain.node.Tool;
import com.mnit.tnt.domain.node.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by jing on 3/1/17.
 */

@RepositoryRestResource(collectionResourceRel = "tool", path = "tool")
public interface ToolRepository extends PagingAndSortingRepository<Tool, Long>{
    List<Tool> findByActive(Boolean active);
    List<Tool> findByOwner(User user);

}

