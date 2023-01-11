package com.merge.assignment.shoppingcart.service.impl;

import com.merge.assignment.shoppingcart.exception.InventoryException;
import com.merge.assignment.shoppingcart.exception.ProductNotFoundException;
import com.merge.assignment.shoppingcart.model.Cart;
import com.merge.assignment.shoppingcart.model.CartLineItem;
import com.merge.assignment.shoppingcart.model.Product;
import com.merge.assignment.shoppingcart.repo.CartRepo;
import com.merge.assignment.shoppingcart.repo.ProductRepo;
import com.merge.assignment.shoppingcart.service.CartService;
import com.merge.assignment.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service(value = "cartService")

public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;


    @Override
    public Cart addProductToUserCart(Long productid, Long userId, int quantity) throws Exception {
        Cart cart = getCartByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
        }
        Product product = productService.findBy(productid);
        if (product.getStock() >= quantity) {
            CartLineItem lineItem = new CartLineItem();
            lineItem.setCart(cart);
            lineItem.setProduct(productService.findBy(productid));
            lineItem.setQuantity(quantity);
            lineItem.setPrice(product.getPrice());
            cart.getLinesItems().add(lineItem);
            cart.setSubtotal(cart.calculateTotal());
            cartRepo.save(cart);
            product.setStock(product.getStock() - quantity);
            productService.save(product);
            return getCartByUserId(userId);
        }
        else
        {
            throw new InventoryException("total stock available is lesser than the quantity requested");
        }

    }

    @Override
    public Cart getCartByUserId(long userId) {
        return cartRepo.findCartByuserId(userId);
    }

    @Override
    public Cart removeProductFromCart(Long productid, Long userid) {
        Cart cart=getCartByUserId(userid);
        Optional<CartLineItem> cartLine=cart.getLinesItems().stream().filter(item->item.getProduct().getProductId()==productid).findFirst();
        Product product=cartLine.get().getProduct();
        product.setStock(product.getStock()+cartLine.get().getQuantity());
        cart.getLinesItems().remove(cartLine.get());
        cart.setSubtotal(cart.calculateTotal());
        productService.save(product);
        return cartRepo.save(cart);
    }

    @Override
    public Cart updateCartQuantity(Long productid, Long userid, int quantity) throws Exception{
        Cart cart=getCartByUserId(userid);
        Optional<CartLineItem> cartLine=cart.getLinesItems().stream().filter(item->item.getProduct().getProductId()==productid).findFirst();
        if(cartLine.isPresent())
        {
            int currentQuantity=cartLine.get().getQuantity();
            int stockDifference=0;
            if(quantity<currentQuantity)
            {
                stockDifference=(currentQuantity-quantity);
            }
            else {
                stockDifference=(quantity-currentQuantity);
            }
            cartLine.get().setQuantity(quantity);
            Product product=cartLine.get().getProduct();
            product.setStock(product.getStock()+stockDifference);
            productService.save(product);
        }
        else
        {
           throw new ProductNotFoundException("Product Id is invalid ");
        }
        cart.setSubtotal(cart.calculateTotal());
        return cartRepo.save(cart);
    }


}


