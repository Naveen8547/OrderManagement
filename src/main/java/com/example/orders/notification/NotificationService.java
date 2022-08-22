package com.example.orders.notification;

import com.example.orders.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private UserService userService;

    public  void send(Long userId){}

}