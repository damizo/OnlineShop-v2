package com.shoponline.repository;

import com.shoponline.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Damian on 2017-02-10.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{

    Product findByTitleIgnoreCase(String title);

    @Query(nativeQuery = true, value = "SELECT max(p.reference_number) FROM products p WHERE p.reference_number LIKE ?1")
    String findByIdentifierMaxLike(String s);
}
