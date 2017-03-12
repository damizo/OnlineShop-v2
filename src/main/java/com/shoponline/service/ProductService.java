package com.shoponline.service;

import com.shoponline.model.dto.ProductCriteriaDTO;
import com.shoponline.model.dto.ProductsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Damian on 2017-02-10.
 */
public interface ProductService {

    ProductsDTO fetchProducts(ProductCriteriaDTO productCriteriaDTO);

}
