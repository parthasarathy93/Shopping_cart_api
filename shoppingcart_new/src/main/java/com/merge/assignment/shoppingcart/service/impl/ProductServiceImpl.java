package com.merge.assignment.shoppingcart.service.impl;

import com.merge.assignment.shoppingcart.exception.ProductNotFoundException;
import com.merge.assignment.shoppingcart.model.Category;
import com.merge.assignment.shoppingcart.model.Product;
import com.merge.assignment.shoppingcart.model.ProductDTO;
import com.merge.assignment.shoppingcart.repo.ProductRepo;
import com.merge.assignment.shoppingcart.service.CategoryService;
import com.merge.assignment.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "productService")

public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryService categoryService;

    @Override
    public Product findBy(Long productId) throws ProductNotFoundException {
       Optional<Product> product=productRepo.findById(productId);
        if(product.isPresent())
        {
            return product.get();
        }
        else
        {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public Product findBy(String description) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> findByCategory(String category) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> findAll() throws ProductNotFoundException {
        List<Product> productList=new ArrayList<>();
        productRepo.findAll().forEach(productList::add);
        return productList;
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product savefromDTO(ProductDTO prod) {

        Product product=prod.getProductfromDTO();
        Category category=categoryService.findBy(prod.categoryid);
        product.setCategory(category);
        return save(product);

    }

    @Override
    public Product updateProduct(Long productId, int stock)throws ProductNotFoundException {
        Optional<Product> prod=productRepo.findById(productId);
        if(prod.isPresent())
        {
            Product product=prod.get();
            product.setStock(stock);
            return save(product);
        }
        else
        {
            throw new ProductNotFoundException("ProductId is invalid ");
        }

    }

}
