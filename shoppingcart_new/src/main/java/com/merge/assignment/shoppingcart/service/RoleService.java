package com.merge.assignment.shoppingcart.service;

import com.merge.assignment.shoppingcart.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role findByName(String name);
}
