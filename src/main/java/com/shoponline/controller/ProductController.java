package com.shoponline.controller;

import com.shoponline.model.dto.ProductCriteriaDTO;
import com.shoponline.model.dto.ProductDTO;
import com.shoponline.model.dto.ProductsDTO;
import com.shoponline.model.entity.Product;
import com.shoponline.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Damian on 2017-02-10.
 */
@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/criteria", method = RequestMethod.POST)
    public ResponseEntity<Collection<ProductDTO>> getProductsByCriteria(@RequestBody ProductCriteriaDTO productCriteriaDTO){
        return ResponseEntity.ok(productService.fetchProducts(productCriteriaDTO));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.create(productDTO));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<ProductDTO>> fetchProducts(){
        return ResponseEntity.ok(productService.fetchProducts());
    }


}
