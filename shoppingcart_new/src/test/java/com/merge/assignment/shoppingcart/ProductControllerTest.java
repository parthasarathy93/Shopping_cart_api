package com.merge.assignment.shoppingcart;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.merge.assignment.shoppingcart.controller.ProductController;
import com.merge.assignment.shoppingcart.exception.ProductNotFoundException;
import com.merge.assignment.shoppingcart.model.Category;
import com.merge.assignment.shoppingcart.model.Product;
import com.merge.assignment.shoppingcart.model.UserDTO;
import com.merge.assignment.shoppingcart.service.ProductService;
import com.merge.assignment.shoppingcart.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;
    @MockBean
    private ProductService productService;





    @Test
    public void getAllProducts() throws Exception {
        Product product=new Product();
        product.setPrice(new BigDecimal(100));
        product.setStock(10);
        product.setProductId(1L);
        product.setDescription("Product1");
        Product product2=new Product();
        product2.setPrice(new BigDecimal(50));
        product2.setStock(10);
        product2.setProductId(2L);
        product2.setDescription("Product2");
        Product product3=new Product();
        product3.setPrice(new BigDecimal(200));
        product3.setStock(50);
        product3.setProductId(3L);
        product3.setDescription("Product1");
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);
        Mockito.when(productService.findAll()).thenReturn(products);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMiIsInJvbGVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjczNDY1NjA4LCJleHAiOjE2NzM0ODM2MDh9.COkAc0QkWAT35OWKFvqSBlQSmVGpppTKpkdNCqkTWN0")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2)));

   }


    @Test
    public void updateproductQty() throws Exception {
        Product product=new Product();
        product.setPrice(new BigDecimal(100));
        product.setStock(20);
        product.setProductId(1L);
        product.setDescription("Product1");
        Mockito.when(productService.updateProduct(product.getProductId(),20)).thenReturn(product);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMiIsInJvbGVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjczNDY1NjA4LCJleHAiOjE2NzM0ODM2MDh9.COkAc0QkWAT35OWKFvqSBlQSmVGpppTKpkdNCqkTWN0")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }




}
