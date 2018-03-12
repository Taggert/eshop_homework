package com.eshop.repository;

import com.eshop.model.User;
import com.eshop.model.web.UserResponce;
import com.eshop.model.web.UserResponceWithoutId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User getByUsername(String username);

    List<User> findAllByLastnameContaining(String lastname);

    User findByEmail(String email);

    @Query("select new com.eshop.model.web.UserResponce(" +
            "u.id," +
            "u.username," +
            "u.email," +
            "u.firstname," +
            "u.lastname) from User u")
    List<UserResponce> findAllOrderByUsername();

    @Query("select new com.eshop.model.web.UserResponceWithoutId(" +
            "u.username," +
            "u.email," +
            "u.firstname," +
            "u.lastname) from User u where u.username = :username")
    UserResponceWithoutId findByUsername(@Param("username") String username);

    @Query("select new com.eshop.model.web.UserResponce(" +
            "u.id," +
            "u.username," +
            "u.email," +
            "u.firstname," +
            "u.lastname) from User u where u.id = :id")
    UserResponce findById(@Param("id") String id);


}