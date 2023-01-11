package com.merge.assignment.shoppingcart.service;

import com.merge.assignment.shoppingcart.exception.ProductNotFoundException;
import com.merge.assignment.shoppingcart.model.Product;
import com.merge.assignment.shoppingcart.model.ProductDTO;

import java.util.List;

public interface ProductService {

    Product findBy(Long productId) throws ProductNotFoundException;
    Product findBy(String description) throws ProductNotFoundException;
    List<Product> findByCategory(String category) throws ProductNotFoundException;
    List<Product> findAll() throws ProductNotFoundException;

    Product save(Product product);

    Product savefromDTO(ProductDTO prod);


    Product updateProduct(Long productId,int stock)throws ProductNotFoundException;
}
