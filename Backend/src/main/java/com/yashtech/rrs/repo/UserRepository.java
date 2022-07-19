package com.yashtech.rrs.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yashtech.rrs.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);
}
