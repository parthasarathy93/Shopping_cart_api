package com.merge.assignment.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long category_id;
    private String description;
    @OneToMany
    private List<Product> products = new ArrayList<Product>();


    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "category_id", unique = true, nullable = false)
    public Long getCategoryId() {
        return this.category_id;
    }

    public void setCategoryId(Long category_id) {
        this.category_id = category_id;
    }

    @Column(name = "description", nullable = false, length = 20)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> productses) {
        this.products = productses;
    }
}
