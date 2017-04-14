package com.mnit.tnt.repository;

import com.mnit.tnt.domain.node.User;
import com.mnit.tnt.domain.relation.Own;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by lihe on 17-4-8.
 */
public interface OwnRepository extends PagingAndSortingRepository<Own, Long> {
    List<Own> findByUser(User user);
    List<Own> findByUser_Id(Long id);
}
