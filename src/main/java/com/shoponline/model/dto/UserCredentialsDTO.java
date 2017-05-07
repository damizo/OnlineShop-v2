package com.shoponline.model.dto;

import com.shoponline.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by dami on 2016-12-24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialsDTO {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Builder.Default
    private UserRole userRole = UserRole.CUSTOMER;

    private String superUserKey;
}
