package com.merge.assignment.shoppingcart.model;

public class CartDTO {

    public Long productid;

    public int quantity;
    public Long userId;


    public Cart getCartFromDTO()
    {
        Cart cart=new Cart();
        cart.setUserId(userId);
        return cart;

    }





}
