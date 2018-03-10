package com.eshop.service;

import com.eshop.model.User;
import com.eshop.model.UserSession;
import com.eshop.model.web.UserLoginRequest;
import com.eshop.model.web.UserRequest;

import java.util.List;

public interface UserService {

    User create(UserRequest user);
    User update(UserRequest user, String sessionId);
    User login(UserLoginRequest userLoginRequest);
    void invalidateSession(String sessionId);
    List<User> getAll();
    User findByUsername(String name);
    void deleteUserByUsername(String username);
    UserSession createUserSession(User tempUser);
    User promoteUser(String username);
    User demoteUser(String username);
    User updateBalance(Double balance, String sessionId);

}