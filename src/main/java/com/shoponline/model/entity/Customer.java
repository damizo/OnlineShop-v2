package com.shoponline.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dami on 2016-12-11.
 */
@Entity
@DiscriminatorValue(value= "CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Order> orders = Collections.emptySet();

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;


}
