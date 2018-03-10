package com.eshop.repository;

import com.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User getByUsername(String username);
    User findByUsername(String username);
    List<User> findAllByLastnameContaining(String lastname);
    User findByEmail(String email);
}