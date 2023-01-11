package com.merge.assignment.shoppingcart.repo;


import com.merge.assignment.shoppingcart.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product,Long> {

}
