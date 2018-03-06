package com.eshop.service;

import com.eshop.model.User;
import com.eshop.model.web.UserRequest;

import java.util.List;

public interface UserService {

    User create(UserRequest user);
    User update(UserRequest user);
    List<User> getAll();
    List<User> findByLastname(String name);
    void deleteUserById(Integer id);

}