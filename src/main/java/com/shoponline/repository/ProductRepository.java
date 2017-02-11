package com.shoponline.repository;

import com.shoponline.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Damian on 2017-02-10.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
