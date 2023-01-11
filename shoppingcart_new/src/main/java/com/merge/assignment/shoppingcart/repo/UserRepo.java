package com.merge.assignment.shoppingcart.repo;

import com.merge.assignment.shoppingcart.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {
    User findByUsername(String username);

    User findUserById(Long userId);

}

