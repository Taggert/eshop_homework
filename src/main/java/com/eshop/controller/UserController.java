package com.eshop.controller;


import com.eshop.model.User;
import com.eshop.model.web.UserRequest;
import com.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody UserRequest userRequest){
       return userService.create(userRequest);
    }

    @PutMapping("/update")
    public User update(@RequestBody UserRequest userRequest){
        return userService.update(userRequest);
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/search/{name}")
    public List<User> searchByName(@PathVariable("name") String name){
        return userService.findByLastname(name);
    }
    @DeleteMapping("/delete/{userId}")
    public void deleteUserById(@PathVariable("userId") Integer userId){
        userService.deleteUserById(userId);
    }

}