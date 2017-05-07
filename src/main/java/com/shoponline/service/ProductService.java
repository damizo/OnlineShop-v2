package com.shoponline.service;

import com.shoponline.model.dto.ProductCriteriaDTO;
import com.shoponline.model.dto.ProductDTO;
import com.shoponline.model.entity.Product;

import java.util.List;
import java.util.Set;

/**
 * Created by Damian on 2017-02-10.
 */
public interface ProductService {

    Set<ProductDTO> fetchProducts(ProductCriteriaDTO productCriteriaDTO);

    Product create(ProductDTO productDTO);

    List<ProductDTO> fetchProducts();
}
