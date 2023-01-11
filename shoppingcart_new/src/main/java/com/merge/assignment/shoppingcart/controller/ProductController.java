package com.merge.assignment.shoppingcart.controller;


import com.merge.assignment.shoppingcart.advice.ApiResponse;
import com.merge.assignment.shoppingcart.model.Cart;
import com.merge.assignment.shoppingcart.model.ProductDTO;
import com.merge.assignment.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;


    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> listProducts()
    {
        try {
            return ResponseEntity.ok(productService.findAll());
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), ""));
        }
    }


    @PreAuthorize("hasRole('ADMIN')")

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO)throws Exception
    {
        return ResponseEntity.ok(productService.savefromDTO(productDTO));
    }


    @PreAuthorize("hasRole('ADMIN')")

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO)throws Exception
    {
        return ResponseEntity.ok(productService.updateProduct(productDTO.productid,productDTO.stock));
    }





}
