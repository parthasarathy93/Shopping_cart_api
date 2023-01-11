package com.merge.assignment.shoppingcart.service.impl;

import com.merge.assignment.shoppingcart.model.Role;
import com.merge.assignment.shoppingcart.repo.RoleRepository;
import com.merge.assignment.shoppingcart.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public Role findByName(String name) {
        Role role = roleRepo.findRoleByName(name);
        return role;
    }
}