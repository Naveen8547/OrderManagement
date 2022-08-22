package com.example.orders.wallet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface  WalletRepository extends CrudRepository<Wallet, Long> {

    public Wallet findByUserId(Long userId);
}
