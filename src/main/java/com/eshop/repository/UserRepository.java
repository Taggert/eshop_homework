package com.eshop.repository;

import com.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByLastnameContaining(String lastname);
    User findByEmail(String email);
}