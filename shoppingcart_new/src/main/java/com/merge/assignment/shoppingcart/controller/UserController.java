package com.merge.assignment.shoppingcart.controller;


import com.merge.assignment.shoppingcart.advice.ApiResponse;
import com.merge.assignment.shoppingcart.model.AuthToken;
import com.merge.assignment.shoppingcart.model.LoginUser;
import com.merge.assignment.shoppingcart.model.User;
import com.merge.assignment.shoppingcart.model.UserDTO;
import com.merge.assignment.shoppingcart.repo.UserRepo;
import com.merge.assignment.shoppingcart.security.TokenProvider;
import com.merge.assignment.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }


    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user){
        try {
            return ResponseEntity.ok(userService.save(user));
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), ""));

        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> listUsers() {
        return ResponseEntity.ok(userService.findAll());
    }



    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/suspend/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> suspend(@PathVariable ("id") Long userId) {
        try {
            return ResponseEntity.ok(userService.suspend(userId));
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), ""));

        }
    }

}
