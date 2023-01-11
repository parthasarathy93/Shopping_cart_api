package com.merge.assignment.shoppingcart.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product")
public class Product implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long product_id;
    private Category category;


    private String description;
    private BigDecimal price;

    private int stock;

    private List<CartLineItem> linesItems = new ArrayList<CartLineItem>();


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id", unique = true, nullable = false)
    public Long getProductId()
    {
        return this.product_id;
    }
    public void setProductId(Long product_id) {
        this.product_id = product_id;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "description", nullable = false, length = 100)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Column(name = "price", nullable = false, precision = 10)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public List<CartLineItem> getLinesItems() {
        return this.linesItems;
    }

    public void setLinesItems(List<CartLineItem> linesItems) {
        this.linesItems = linesItems;
    }

}