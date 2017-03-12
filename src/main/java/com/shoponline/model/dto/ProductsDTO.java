package com.shoponline.model.dto;

import com.shoponline.model.entity.Product;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Damian on 2017-02-10.
 */
public class ProductsDTO {

   Set<ProductDTO> products = new HashSet<ProductDTO>();

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }
}
