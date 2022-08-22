package com.example.orders.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String useName) throws UsernameNotFoundException{
        Optional<UserAuth> userAuth=userAuthRepository.findByUsername(useName);
        userAuth.orElseThrow(()->new UsernameNotFoundException("Not found"+useName));
        return userAuth.map(MyUserDetails::new).get();
    }

    public void addUserAuth(UserAuth userAuth)
    {
        userAuthRepository.save(userAuth);
    }


}
