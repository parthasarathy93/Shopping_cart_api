package com.merge.assignment.shoppingcart.controller;


import com.merge.assignment.shoppingcart.advice.ApiResponse;
import com.merge.assignment.shoppingcart.model.CategoryDTO;
import com.merge.assignment.shoppingcart.repo.CategoryRepo;
import com.merge.assignment.shoppingcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    private CategoryRepo categoryRepo;


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> listCategories()
    {
        try {
            return ResponseEntity.ok(categoryService.findAll());
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), ""));
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO)
    {
        try {
            return ResponseEntity.ok(categoryService.save(categoryDTO.description));
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), ""));
        }
    }


}
