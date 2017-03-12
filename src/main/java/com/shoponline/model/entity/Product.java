package com.shoponline.model.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by Damian on 2017-02-10.
 */
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Order> order = Collections.emptyList();

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "quantity")
    private Integer quantity;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
