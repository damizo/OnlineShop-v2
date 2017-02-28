package com.shoponline.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Damian on 2017-02-14.
 */
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @ManyToMany
    @JoinTable(name = "order_products", joinColumns = {
            @JoinColumn(name = "order_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id",
                            nullable = false, updatable = false)
            })
    private List<Product> products = Collections.emptyList();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
