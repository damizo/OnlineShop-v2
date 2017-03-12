package com.shoponline.service;

import com.shoponline.model.dto.ProductCriteriaDTO;
import com.shoponline.model.dto.ProductsDTO;
import com.shoponline.model.entity.Product;
import com.shoponline.repository.ProductRepository;
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

/**
 * Created by Damian on 2017-02-10.
 */
@Transactional
@Service
public class ProductServiceImpl extends QueryBuilder<Product> implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductsDTO fetchProducts(ProductCriteriaDTO productCriteriaDTO) {
        Root<Product> productRoot = getRoot();
        ProductsDTO productsDTO = new ProductsDTO();
        Query query = entityManager.createQuery(criteriaQuery.select(productRoot)
                .where(criteriaBuilder
                        .like(productRoot.get("title"), productCriteriaDTO.getTitle()))
                .where(criteriaBuilder
                        .greaterThan(productRoot.get("price"), productCriteriaDTO.getPriceFrom()))
                .where(criteriaBuilder
                        .lessThan(productRoot.get("price"), productCriteriaDTO.getPriceTo())));
        return null;
    }
}
