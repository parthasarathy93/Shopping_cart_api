package com.merge.assignment.shoppingcart.repo;

import com.merge.assignment.shoppingcart.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends CrudRepository<Cart, Long> {
     Cart findCartByuserId(Long userid);
}
