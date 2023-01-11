package com.merge.assignment.shoppingcart;


import com.merge.assignment.shoppingcart.controller.ProductController;
import com.merge.assignment.shoppingcart.exception.ProductNotFoundException;
import com.merge.assignment.shoppingcart.model.Category;
import com.merge.assignment.shoppingcart.model.Product;
import com.merge.assignment.shoppingcart.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
@WebMvcTest(ProductService.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getAllProducts() throws ProductNotFoundException {
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
        product3.setDescription("Product3");
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);
        given(productService.findAll()).willReturn(products);
   }



}
