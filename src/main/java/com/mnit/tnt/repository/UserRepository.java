package com.mnit.tnt.repository;

import com.mnit.tnt.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by lihe on 2/12/17.
 */

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

}

//curl -i -X POST -H "Content-Type:application/json" -d '{  "userName": "someone", "password": "somepass", "firstName" : "Frodo1",  "lastName" : "Baggins1", "email": "s@a.com", "stormPathHref": "https://stormpath", "valid": true, "dateCreated": null, "dateUpdated": null }' http://localhost:8080/users
