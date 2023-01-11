package com.merge.assignment.shoppingcart.controller;


import com.merge.assignment.shoppingcart.advice.ApiResponse;
import com.merge.assignment.shoppingcart.model.*;
import com.merge.assignment.shoppingcart.service.CartService;
import com.merge.assignment.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createCart(@RequestBody CartDTO cart) {
         try {
             UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
             return ResponseEntity.ok(cartService.addProductToUserCart(cart.productid,userService.findOne(userDetails.getUsername()).getId(),cart.quantity));
         } catch (Exception ex) {
             return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), ""));
         }
     }

    /**
     * Handles the mapping /products
     * To edit atj
     * @param cart
     * @return
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<?>  updateCart(@RequestBody CartDTO cart)  {
         try {
             UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
             return ResponseEntity.ok(cartService.updateCartQuantity(cart.productid, userService.findOne(userDetails.getUsername()).getId(), cart.quantity));
         }
         catch(Exception ex)
         {
             return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), ""));
         }
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("{productId}")
    public ResponseEntity<?> removeProductfromCart(@PathVariable ("productId") Long productId)  {
        try {
            UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(cartService.removeProductFromCart(productId,userService.findOne(userDetails.getUsername()).getId()));
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), ""));
        }
     }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Cart listCart() {
        try {
            Cart cartObj=this.cartService.getCartByUserId(1L);
            return cartObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
