package com.shoponline.model.entity;

import com.shoponline.model.enums.UserRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by dami on 2016-12-11.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@Data
public abstract class User extends BaseEntity {

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.NONE)
    private UserRole role;

}
