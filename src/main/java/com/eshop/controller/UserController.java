package com.eshop.controller;


import com.eshop.model.User;
import com.eshop.model.exceptions.InputValidationException;
import com.eshop.model.web.UserLoginRequest;
import com.eshop.model.web.UserRequest;
import com.eshop.model.web.UserResponce;
import com.eshop.model.web.UserResponceWithoutId;
import com.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody @Valid UserRequest userRequest, BindingResult result){
        if(result.hasErrors()){
            throw new InputValidationException(result);
        }
       return userService.create(userRequest);
    }

    @PostMapping("/login")
    public User login(@RequestBody @Valid UserLoginRequest userLoginRequest, BindingResult result){
        if(result.hasErrors()){
            throw new InputValidationException(result);
        }
        return userService.login(userLoginRequest);
    }

    @PutMapping("/update")
    public User update(String username, @RequestBody @Valid UserRequest userRequest, BindingResult result,
    @RequestHeader("Authorization") String sessionId){
        if(result.hasErrors()){
            throw new InputValidationException(result);
        }
        return userService.update(userRequest, sessionId);
    }

    @PutMapping("/balance")
    public UserResponce balance(@RequestBody Double balance, @RequestHeader("Authorization") String sessionId ){
       return userService.updateBalance(balance, sessionId);
    }

    @GetMapping("/all")
    public List<UserResponce> getAll(){
        return userService.getAll();
    }

    @GetMapping("/search/{username}")
    public UserResponceWithoutId searchByUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }

    @DeleteMapping("/delete/{username}")
    public void deleteUserByUsername(@PathVariable("username") String username){
        userService.deleteUserByUsername(username);
    }

    @GetMapping("/promote/{username}")
    public UserResponce promoteUser(@PathVariable("username") String username){
        return userService.promoteUser(username);
    }

    @GetMapping("/demote/{username}")
    public UserResponce demoteUser(@PathVariable("username") String username){
        return userService.demoteUser(username);
    }

}