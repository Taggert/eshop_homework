package com.eshop.service.Impl;

import com.eshop.model.Role;
import com.eshop.model.User;
import com.eshop.model.UserSession;
import com.eshop.model.exceptions.NoSuchUserException;
import com.eshop.model.web.UserLoginRequest;
import com.eshop.model.web.UserRequest;
import com.eshop.repository.UserRepository;
import com.eshop.repository.UserSessionRepository;
import com.eshop.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    @Transactional
    public User create(UserRequest userWeb) {
        User tempUser = new User();
        List<Role> rolesTemp = new ArrayList<>();
        if (userRepository.findAll().size() == 0) {
            rolesTemp.add(Role.BUYER);
            rolesTemp.add(Role.SELLER);
            rolesTemp.add(Role.ADMIN);
        } else {
            rolesTemp.add(Role.BUYER);
        }
        tempUser = tempUser.builder()
                .username(userWeb.getUsername())
                .password(userWeb.getPassword())
                .email(userWeb.getEmail())
                .firstname(userWeb.getFirstname())
                .lastname(userWeb.getLastname())
                .roles(rolesTemp)
                .balance(0.)
                .build();
        userRepository.save(tempUser);
        createUserSession(tempUser);
        return tempUser;
    }

    @Override
    @Transactional
    public void invalidateSession(String sessionId) {
        UserSession userSession = userSessionRepository.findBySessionId(sessionId);
        if (userSession == null) {
            return;
        }
        userSession.setIsValid(false);
    }

    @Override
    @Transactional
    public User update(UserRequest userWeb, String sessionId) {
        if (userSessionRepository.getBySessionId(sessionId).equals(userRepository.getByUsername(userWeb.getUsername()))) {
            User tempUser = userRepository.findByEmail(userWeb.getEmail());
            tempUser = tempUser == null ? new User() : tempUser;
            if (userWeb.getFirstname() != null) {
                tempUser.setFirstname(userWeb.getFirstname());
            }
            if (userWeb.getLastname() != null) {
                tempUser.setLastname(userWeb.getLastname());
            }
            if (userWeb.getEmail() != null) {
                tempUser.setEmail(userWeb.getEmail());
            }
            userRepository.save(tempUser);
            return tempUser;
        }
        throw new NoSuchUserException("Only user "+userWeb.getUsername()+" can update himself");
    }

    @Override
    @Transactional
    public User login(UserLoginRequest userLoginRequest) {
        User user = userRepository.getByUsername(userLoginRequest.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Login unabled, user not found");
        }
        createUserSession(user);
        return user;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String username) {
        userRepository.delete(userRepository.findByUsername(username));
    }

    @Override
    @Transactional
    public UserSession createUserSession(User tempUser) {
        UserSession userSession1 = userSessionRepository.getBySessionId(tempUser.getId());
        if (userSession1 == null) {
            UserSession userSession = new UserSession();
            userSession = userSession.builder()
                    .sessionId(tempUser.getId())
                    .user(tempUser)
                    .isValid(true)
                    .build();
            userSessionRepository.save(userSession);
            return userSession;
        }
        userSession1.setIsValid(true);
        return userSession1;
    }

    @Override
    @Transactional
    public User promoteUser(String username) {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new RuntimeException(String.format("User with username: %s, not found", username));
        }
        List<Role> rolesTemp = user.getRoles();
        if (rolesTemp.get(rolesTemp.size() - 1).equals(Role.ADMIN)) {
            throw new RuntimeException(String.format("User with username: %s, is Admininstrator", username));
        }
        rolesTemp.add(Role.getById(rolesTemp.get(rolesTemp.size() - 1).getId() + 1));
        user.setRoles(rolesTemp);
        return user;
    }

    @Override
    @Transactional
    public User demoteUser(String username) {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new RuntimeException(String.format("User with username: %s, not found", username));
        }
        List<Role> rolesTemp = user.getRoles();
        if (rolesTemp.get(rolesTemp.size() - 1).equals(Role.BUYER)) {
            throw new RuntimeException(String.format("User with username: %s, isn't able to be demoted, user is Buyer", username));
        }
        rolesTemp.remove(rolesTemp.get(rolesTemp.size() - 1));
        user.setRoles(rolesTemp);
        return user;
    }

    @Override
    @Transactional
    public User updateBalance(Double balance, String sessionId) {
        User user = userSessionRepository.getBySessionId(sessionId).getUser();
        Double balanceOld = user.getBalance();
        user.setBalance(balance + balanceOld);
        userRepository.save(user);
        return user;
    }
}