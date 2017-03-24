package com.shoponline.service;

import com.shoponline.model.dto.ProductCriteriaDTO;
import com.shoponline.model.dto.ProductDTO;
import com.shoponline.model.dto.ProductsDTO;
import com.shoponline.model.entity.Product;
import com.shoponline.repository.ProductRepository;
import com.shoponline.utils.JsonUtils;
import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Damian on 2017-02-10.
 */
@Transactional
@Service
public class ProductServiceImpl extends QueryBuilder<Product> implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductsDTO fetchProducts(String encodedJson) {
        ProductCriteriaDTO productCriteriaDTO = JsonUtils.decodeBase64(encodedJson);

        Root<Product> productRoot = getRoot();
        Set<ProductDTO> products = new HashSet<>();
        ProductsDTO productsDTO = new ProductsDTO();

        CriteriaQuery criteria = getCriteriaQuery(productCriteriaDTO, productRoot);
        List<Product> filteredProducts = entityManager.createQuery(criteria).getResultList();

        filteredProducts.forEach(product -> {
            ProductDTO productDTO = new ProductDTO.ProductDTOBuilder()
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .referenceNumber(product.getReferenceNumber())
                    .title(product.getTitle())
                    .build();
            products.add(productDTO);
        });

        productsDTO.setProducts(products);
        return productsDTO;
    }

    private CriteriaQuery getCriteriaQuery(ProductCriteriaDTO productCriteriaDTO, Root<Product> productRoot) {
        CriteriaQuery criteria = criteriaQuery.select(productRoot)
                .where(criteriaBuilder
                        .like(productRoot.get("title"), productCriteriaDTO.getTitle()))
                .where(criteriaBuilder
                        .greaterThan(productRoot.get("price"), productCriteriaDTO.getPriceFrom()))
                .where(criteriaBuilder
                        .lessThan(productRoot.get("price"), productCriteriaDTO.getPriceTo()));


        if (productCriteriaDTO.getPriceSorting()) {
            criteria = criteria.orderBy(criteriaBuilder.desc(productRoot.get("price")));
        }

        if (productCriteriaDTO.getRatingSorting()) {
            criteria = criteria.orderBy(criteriaBuilder.desc(productRoot.get("rating")));
        }
        return criteria;
    }
}
