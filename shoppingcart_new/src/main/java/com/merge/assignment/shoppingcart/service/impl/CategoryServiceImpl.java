package com.merge.assignment.shoppingcart.service.impl;

import com.merge.assignment.shoppingcart.model.Category;
import com.merge.assignment.shoppingcart.repo.CategoryRepo;
import com.merge.assignment.shoppingcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo catrepo;


    @Override
    public Category findBy(Long categId) {
        return catrepo.findCategoryByCategoryId(categId);
    }


    @Override
    public Category save(String dec)
    {
        Category category=new Category();
        category.setDescription(dec);
        return catrepo.save(category);
    }

    @Override
    public List<Category> findAll() {
        List<Category> catList=new ArrayList<>();
       catrepo.findAll().iterator().forEachRemaining(catList::add);
       return catList;

    }

}
