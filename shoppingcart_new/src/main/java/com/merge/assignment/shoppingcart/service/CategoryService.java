package com.merge.assignment.shoppingcart.service;

import com.merge.assignment.shoppingcart.model.Category;

import java.util.List;

public interface CategoryService {

    Category findBy(Long categId);

    Category save(String category);

    List<Category> findAll();
}
