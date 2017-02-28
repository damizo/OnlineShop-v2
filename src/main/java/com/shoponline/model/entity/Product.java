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

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
