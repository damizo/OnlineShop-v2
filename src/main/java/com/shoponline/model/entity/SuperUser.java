package com.shoponline.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by dami on 2016-12-11.
 */
@Entity
@DiscriminatorValue(value = "SUPER_USER")
public class SuperUser extends User {

}
