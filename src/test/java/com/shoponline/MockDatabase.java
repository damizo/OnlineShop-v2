package com.shoponline;

import com.shoponline.model.dto.ProductDTO;
import com.shoponline.model.dto.UserCredentialsDTO;
import com.shoponline.model.entity.*;
import com.shoponline.model.enums.CurrencyType;
import com.shoponline.model.enums.UserRole;
import com.shoponline.utils.CryptoUtils;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Damian on 2017-04-09.
 */
public class MockDatabase {
    public static final List<Product> PRODUCTS_ENTITIES = Arrays.asList(
            new Product("P00001", new BigDecimal(5000), "Sony Xperia", 3),
            new Product("P00002", new BigDecimal(2000), "iPhone 3S", 2),
            new Product("P00003", new BigDecimal(1000), "Samsung TV", 7),
            new Product("P00004", new BigDecimal(500.50), "Toshiba HDD", 5)
    );

    public static final List<Customer> CUSTOMER_ENTITIES = Arrays.asList(
            new Customer(null, "Andrew", "Golota", "Karolkowa Street"),
            new Customer(null, "Maciej", "Nowak", "Andrzejowa Street")
    );

    public static final List<ProductDTO> PRODUCTS_DTO = Arrays.asList(
            ProductDTO.builder().referenceNumber("P00001").price(new BigDecimal(5000)).title("Sony Xperia")
                    .quantity(3)
                    .currency(CurrencyType.PLN)
                    .build(),
            ProductDTO.builder().referenceNumber("P00002").price(new BigDecimal(2000)).title("iPhone 3S")
                    .quantity(2)
                    .currency(CurrencyType.PLN)
                    .build(),
            ProductDTO.builder().referenceNumber("P00003").price(new BigDecimal(1000)).title("Samsung TV")
                    .quantity(7)
                    .currency(CurrencyType.PLN)
                    .build(),
            ProductDTO.builder().referenceNumber("P00004").price(new BigDecimal(500.50)).title("Toshiba HDD")
                    .quantity(5)
                    .currency(CurrencyType.PLN)
                    .build()
    );

    public static final List<UserCredentialsDTO> USERS_DTO = Arrays.asList(
            new UserCredentialsDTO("andrzej@onet.pl", "KSS125", UserRole.CUSTOMER, null),
            new UserCredentialsDTO("dk@onet.pl", "ASDKAD@", UserRole.SUPER_USER, CryptoUtils.SUPER_USER_KEY)
    );

    public static <T extends User> T setUpUserByDTO(UserCredentialsDTO potentialNewUser, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T user = clazz.newInstance();
        user.setUserName(potentialNewUser.getEmail());
        user.setPassword(CryptoUtils.SHA256(potentialNewUser.getPassword()));
        return user;
    }

}
