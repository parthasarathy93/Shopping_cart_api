package com.merge.assignment.shoppingcart.model;


import static javax.persistence.GenerationType.IDENTITY;
import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "cartlineitem")
public class CartLineItem implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long item_id;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;
    private Integer quantity;
    private BigDecimal price;

    @Column(name = "item_id", unique = true, nullable = false)
    public Long getItem_id() {
        return this.item_id;
    }

    public void setIdlinesItem(Long item_id) {
        this.item_id = item_id;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "price", nullable = false, precision = 10)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}