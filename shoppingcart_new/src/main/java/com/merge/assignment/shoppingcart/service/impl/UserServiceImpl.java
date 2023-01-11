package com.merge.assignment.shoppingcart.service.impl;

import java.util.*;

import com.merge.assignment.shoppingcart.exception.UserSuspendedException;
import com.merge.assignment.shoppingcart.model.Role;
import com.merge.assignment.shoppingcart.model.User;
import com.merge.assignment.shoppingcart.model.UserDTO;
import com.merge.assignment.shoppingcart.repo.UserRepo;
import com.merge.assignment.shoppingcart.service.RoleService;
import com.merge.assignment.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {


    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepo userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        if(user.isActive()) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
        }
        else {
            throw new UsernameNotFoundException("User suspended");
        }

    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;

    }



    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User save(UserDTO userDto)throws Exception {
        User newUser =userDto.getUserFromDto();
        if(userDao.findByUsername(userDto.getUsername())==null) {
            newUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));

            Role role = roleService.findByName(userDto.getRole());
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);

            newUser.setRoles(roleSet);
            return userDao.save(newUser);
        }
        else {
            throw new Exception("User already exists");
        }
    }

    @Override
    public User suspend(Long userId)
    {
        User user=userDao.findUserById(userId);
        user.suspend();
        return userDao.save(user);
    }

}