package com.shoponline.controller;

import com.shoponline.model.dto.ProductCriteriaDTO;
import com.shoponline.model.dto.ProductsDTO;
import com.shoponline.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Damian on 2017-02-10.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/criteria/{encoded_json}", method = RequestMethod.GET)
    public ResponseEntity<ProductsDTO> getProductsByCriteria(@RequestParam("encoded_json") String encodedJson){
        return ResponseEntity.ok(productService.fetchProducts(encodedJson));
    }


}
