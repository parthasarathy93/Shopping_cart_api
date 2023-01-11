package com.merge.assignment.shoppingcart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merge.assignment.shoppingcart.model.Cart;
import com.merge.assignment.shoppingcart.model.CartLineItem;
import com.merge.assignment.shoppingcart.model.Category;
import com.merge.assignment.shoppingcart.model.Product;
import com.merge.assignment.shoppingcart.repo.CartRepo;
import com.merge.assignment.shoppingcart.repo.CategoryRepo;
import com.merge.assignment.shoppingcart.service.CartService;
import com.merge.assignment.shoppingcart.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    CartService cartService;

    @Test
    public void addProductToCart()throws Exception
    {
        Category category=new Category();
        category.setCategoryId(1L);
        category.setDescription("dress");
        Product product=new Product();
        product.setProductId(1L);
        product.setStock(10);
        product.setPrice(new BigDecimal(100));
        product.setCategory(category);
        CartLineItem cartLineItem=new CartLineItem();
        cartLineItem.setProduct(product);
        cartLineItem.setQuantity(5);
        cartLineItem.setPrice(new BigDecimal(100));
        cartLineItem.setIdlinesItem(1L);
        List<CartLineItem> cItems=new ArrayList<>();
        cItems.add(cartLineItem);
        Cart cart=new Cart();
        cart.setLinesItems(cItems);
        cart.setUserId(1L);
        cart.setSubtotal(cart.getSubtotal());
        Mockito.when(cartService.addProductToUserCart(1L,1L,20)).thenReturn(cart);
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/carts")
                .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMiIsInJvbGVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjczNDY1NjA4LCJleHAiOjE2NzM0ODM2MDh9.COkAc0QkWAT35OWKFvqSBlQSmVGpppTKpkdNCqkTWN0")
                .contentType(MediaType.APPLICATION_JSON).content("{\"productid\":1,\"quantity\":9}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("cartId");


    }


   @Test
    public void updateQtyToCart()throws Exception {
       Category category=new Category();
       category.setCategoryId(1L);
       category.setDescription("dress");
       Product product=new Product();
       product.setProductId(1L);
       product.setStock(10);
       product.setPrice(new BigDecimal(100));
       product.setCategory(category);
       CartLineItem cartLineItem=new CartLineItem();
       cartLineItem.setProduct(product);
       cartLineItem.setQuantity(6);
       cartLineItem.setPrice(new BigDecimal(100));
       cartLineItem.setIdlinesItem(1L);
       List<CartLineItem> cItems=new ArrayList<>();
       cItems.add(cartLineItem);
       Cart cart=new Cart();
       cart.setLinesItems(cItems);
       cart.setUserId(1L);
       cart.setSubtotal(cart.getSubtotal());
       Mockito.when(cartService.updateCartQuantity(1L,1L,20)).thenReturn(cart);
       mvc.perform(MockMvcRequestBuilders.put("/api/v1/carts")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMiIsInJvbGVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjczNDY1NjA4LCJleHAiOjE2NzM0ODM2MDh9.COkAc0QkWAT35OWKFvqSBlQSmVGpppTKpkdNCqkTWN0")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"productid\":1,\"quantity\":6}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("cartId");
    }

}
