package com.ces.slc.workshop.security.application;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.security.domain.User;

@Repository
public interface UserRepository extends ListCrudRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> getUserByUsername(@Param("username") String username);
}
