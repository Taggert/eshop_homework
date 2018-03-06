package com.eshop.service.Impl;

import com.eshop.model.User;
import com.eshop.model.web.UserRequest;
import com.eshop.repository.UserRepository;
import com.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(UserRequest userWeb) {
        User tempUser = new User();
        tempUser = tempUser.builder()
                .email(userWeb.getEmail())
                .firstname(userWeb.getFirstname())
                .lastname(userWeb.getLastname())
                .balance(userWeb.getBalance() == null ? 0. :userWeb.getBalance())
                .build();
        userRepository.save(tempUser);
        return tempUser;
    }

    @Override
    @Transactional
    public User update(UserRequest userWeb) {
       User tempUser = userRepository.findByEmail(userWeb.getEmail());
       tempUser = tempUser == null ? new User() : tempUser;
        if(userWeb.getFirstname() != null){
            tempUser.setFirstname(userWeb.getFirstname());
        }
        if(userWeb.getLastname() != null){
            tempUser.setLastname(userWeb.getLastname());
        }
        if(userWeb.getEmail() != null){
            tempUser.setEmail(userWeb.getEmail());
        }
        userRepository.save(tempUser);
        return tempUser;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public List<User> findByLastname(String lastname) {
        return userRepository.findAllByLastnameContaining(lastname);
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}