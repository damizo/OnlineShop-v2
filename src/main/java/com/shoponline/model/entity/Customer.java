package com.shoponline.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by dami on 2016-12-11.
 */
@Entity
@DiscriminatorValue(value= "CUSTOMER")
public class Customer extends User{

    private String name;

    private String lastName;

    private String address;
    
}
