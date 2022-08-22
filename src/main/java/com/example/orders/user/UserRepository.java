package com.example.orders.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    //    public List<User> findByUserId(Long userId);
    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);
}