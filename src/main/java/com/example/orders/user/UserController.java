package com.example.orders.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/users")
    public List<User> getAllUsers() {

        return userService.getAllUsers();


    }


    @RequestMapping("/users/{id}")

    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody UserCreateRequest userCreateRequest) {

        return userService.addUser(userCreateRequest);

    }


}

