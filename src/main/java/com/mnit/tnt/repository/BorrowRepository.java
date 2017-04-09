package com.mnit.tnt.repository;

import com.mnit.tnt.domain.relation.Borrow;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lihe on 17-4-8.
 */
public interface BorrowRepository extends PagingAndSortingRepository<Borrow, Long> {
}
