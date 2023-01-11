package com.merge.assignment.shoppingcart.service;

import com.merge.assignment.shoppingcart.model.User;
import com.merge.assignment.shoppingcart.model.UserDTO;
import com.merge.assignment.shoppingcart.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface UserService {

    User save(UserDTO user)throws Exception;
    List<User> findAll();
    User findOne(String username);

    User suspend(Long userId);
}
