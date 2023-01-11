package com.merge.assignment.shoppingcart.model;

import java.math.BigDecimal;

public class ProductDTO {
    public String description;

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public Long productid;
    public int stock;


    public String getDescription() {
        return description;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public BigDecimal price;
    public Long categoryid;

    public Product getProductfromDTO()
    {
        Product product=new Product();
        product.setStock(stock);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }


}
