package com.shoponline.model.dto;

import com.shoponline.model.enums.UserRole;

/**
 * Created by dami on 2016-12-24.
 */
public class UserCredentialsDTO {

    private String userName;

    private String password;

    private UserRole userRole;

    private String superUserKey;

    public String getSuperUserKey() {
        return superUserKey;
    }

    public void setSuperUserKey(String superUserKey) {
        this.superUserKey = superUserKey;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

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

    @Override
    public String toString(){
        return "username - " + userName + ", password - " + password + ", role - " + userRole.name();
    }
}
