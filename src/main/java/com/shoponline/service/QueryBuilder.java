package com.shoponline.service;

import com.shoponline.model.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by Damian on 2017-03-12.
 */
public abstract class QueryBuilder<T extends BaseEntity> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected CriteriaBuilder criteriaBuilder;

    @Autowired
    protected CriteriaQuery criteriaQuery;

    private T t;

    protected Root<T> getRoot(){
        return criteriaQuery.from(t.getClass());
    }

}