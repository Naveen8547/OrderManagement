package com.example.orders.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {

    @Autowired
    MyUserDetailService myUserDetailService;

    @RequestMapping(method = RequestMethod.POST,value = "/userAuth")
    public Void addUserAuth(@RequestBody UserAuth userAuth)
    {
       myUserDetailService.addUserAuth(userAuth);
      return null;
    }




}
