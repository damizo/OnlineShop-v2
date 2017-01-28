package com.shoponline.model.entity;

import com.shoponline.model.enums.UserRole;

import javax.persistence.*;

/**
 * Created by dami on 2016-12-11.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public abstract class User extends BaseEntity {

    private String userName;

    private String password;

    @Column(insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
