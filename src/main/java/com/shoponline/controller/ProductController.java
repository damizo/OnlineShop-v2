package com.shoponline.controller;

import com.shoponline.model.dto.ProductCriteriaDTO;
import com.shoponline.model.dto.ProductsDTO;
import com.shoponline.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Damian on 2017-02-10.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ProductsDTO> getProductsByCriterias(@RequestBody ProductCriteriaDTO productCriteriaDTO){
        return null;
    }


}
