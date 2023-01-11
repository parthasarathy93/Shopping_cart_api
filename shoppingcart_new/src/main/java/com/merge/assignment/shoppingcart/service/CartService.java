package com.merge.assignment.shoppingcart.service;

import com.merge.assignment.shoppingcart.model.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    /**
     * Add new Product to user Cart
     * @param productid
     * @param userid
     * @param quantity
     * @return
     * @throws Exception
     */
    Cart addProductToUserCart(Long productid,Long userid, int quantity) throws Exception;

    /**
     * Get the cart of a user
     * @param userId
     * @return
     */
    Cart getCartByUserId(long userId);

    /**
     * Remove the product from the cart
     * @param productid
     * @param userid
     * @return
     */
    Cart removeProductFromCart(Long productid,Long userid);

    /**
     * Update the quantity of a product in the user's cart
     * @param productid
     * @param userid
     * @param quantity
     * @return
     * @throws Exception
     */
    Cart updateCartQuantity(Long productid,Long userid,int quantity)throws Exception;
}
