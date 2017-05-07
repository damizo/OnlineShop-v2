package com.shoponline.repository;

import com.shoponline.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * Created by dami on 2016-12-11.
 */
@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    T findByUserName(String userName);

    T findByUserNameAndPassword(String userName, String password);
}
