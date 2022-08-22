package com.example.orders.user;

import com.example.orders.exceptionHandling.EmptyInputException;
import com.example.orders.inventory.InventoryService;
import com.example.orders.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private WalletService walletService;


  @Cacheable("User")
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);
        return users;
    }




    public String addUser(UserCreateRequest userCreateRequest) {
        User user = new User(userCreateRequest.getUserName(), userCreateRequest.getPhoneNumber(), userCreateRequest.getEmail(), userCreateRequest.getAge(), userCreateRequest.getGender(), userCreateRequest.getNationality());
         if(userCreateRequest.getUserName().isEmpty()||userCreateRequest.getPhoneNumber().isEmpty()||userCreateRequest.getEmail().isEmpty()||userCreateRequest.getNationality().isEmpty())
         {
             throw new EmptyInputException();
         }
        if (emailAlreadyExist(userCreateRequest.getEmail()) == 1) {
            return "email already exist";
        } else if (phoneNumberAlreadyExist(userCreateRequest.getPhoneNumber()) == 1) {
            return "phone Number already exist";

        } else userRepository.save(user);
        return "user added successfully";
    }


    public int emailAlreadyExist(String email) {

        User user = userRepository.findByEmail(email);
        if (user == null)
            return 0;
        else return 1;

    }


    public int phoneNumberAlreadyExist(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null)
            return 0;
        else return 1;

    }



    public User getUser(Long userId) {

        System.out.println("Db");

        return userRepository.findById(userId).get();


    }

}
