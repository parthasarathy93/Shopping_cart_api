package com.merge.assignment.shoppingcart.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "cart")
public class Cart implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cart_id;


    private Long userId;

    private BigDecimal subtotal;
    @OneToMany(fetch = FetchType.EAGER,cascade = javax.persistence.CascadeType.ALL)
    private List<CartLineItem> linesItems = new ArrayList<CartLineItem>();

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getCartId() {
        return this.cart_id;
    }

    public void setCardId(Long idCart) {
        this.cart_id = idCart;
    }



    public BigDecimal getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    @Cascade(CascadeType.ALL)
    public List<CartLineItem> getLinesItems() {
        return this.linesItems;
    }

    public void setLinesItems(List<CartLineItem> linesItems) {
        this.linesItems = linesItems;
    }

    public BigDecimal calculateTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for (CartLineItem lineItem : this.getLinesItems()) {
            total=total.add(lineItem.getPrice().multiply(new BigDecimal(lineItem.getQuantity())));
        }
        return total;
    }
}