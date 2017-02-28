package com.shoponline.model.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dami on 2016-12-11.
 */
@Entity
@DiscriminatorValue(value= "CUSTOMER")
public class Customer extends User{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Order> orders = Collections.emptySet();

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
