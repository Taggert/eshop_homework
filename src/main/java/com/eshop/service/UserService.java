package com.eshop.service;

import com.eshop.model.User;
import com.eshop.model.UserSession;
import com.eshop.model.web.UserLoginRequest;
import com.eshop.model.web.UserRequest;
import com.eshop.model.web.UserResponce;
import com.eshop.model.web.UserResponceWithoutId;

import java.util.List;

public interface UserService {


    User create(UserRequest user);
    User update(UserRequest user, String sessionId);
    User login(UserLoginRequest userLoginRequest);
    void invalidateSession(String sessionId);
    List<UserResponce> getAll();
    UserResponceWithoutId findByUsername(String name);
    void deleteUserByUsername(String username);
    UserSession createUserSession(User tempUser);
    UserResponce promoteUser(String username);
    UserResponce demoteUser(String username);
    UserResponce updateBalance(Double balance, String sessionId);

}