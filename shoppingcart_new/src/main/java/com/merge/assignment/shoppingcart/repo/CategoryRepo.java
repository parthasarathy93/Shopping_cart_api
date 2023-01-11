package com.merge.assignment.shoppingcart.repo;

import com.merge.assignment.shoppingcart.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CategoryRepo extends CrudRepository<Category,Long> {
    Category findCategoryByCategoryId(Long categoryId);
}
