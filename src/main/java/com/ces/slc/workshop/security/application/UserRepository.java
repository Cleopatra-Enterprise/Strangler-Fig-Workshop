package com.ces.slc.workshop.security.application;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.security.domain.User;

@Repository
public interface UserRepository extends ListCrudRepository<User, String> {

}
