package com.shoponline.model.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by dami on 2016-12-11.
 */
@Entity
@DiscriminatorValue(value = "SUPER_USER")
@Data
public class SuperUser extends User {

}
