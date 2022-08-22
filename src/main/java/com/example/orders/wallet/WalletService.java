package com.example.orders.wallet;

import com.example.orders.response.Response;
import com.example.orders.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getAllWallets() {

        List<Wallet> wallets = new ArrayList<>();
        walletRepository.findAll()
                .forEach(wallets::add);
        return wallets;
    }

    public void addWallet(User user) {
        Wallet wallet=new Wallet(0L,user);
        walletRepository.save(wallet);
    }

    public  void updateWallet(Long walletId, Float price){

        Wallet wallet=walletRepository.findById(walletId).get();
        Float bal=wallet.getWalletBalance();
        bal=bal - price;

        wallet.setWalletBalance(bal);
        walletRepository.save(wallet);


    }
    public  void reWallet(Long walletId, Float price){

        Wallet wallet=walletRepository.findById(walletId).get();
        Float bal=wallet.getWalletBalance();
        bal=bal + price;

        wallet.setWalletBalance(bal);
        walletRepository.save(wallet);


    }
    public  Wallet getWallet(Long walletId)
    {
        return walletRepository.findById(walletId).get();
    }

    public Float getWalletBalance(Long userId)
    {


            return walletRepository.findByUserId(userId).getWalletBalance();


    }

    public  Wallet getWalletByUserId(Long userId)
    {
        return walletRepository.findByUserId(userId);
    }

    public String
    topUpWallet(WalletTopUpRequest walletTopUpRequest)
    {

           Wallet wallet = walletRepository.findByUserId(walletTopUpRequest.getUserId());
           float bal = wallet.getWalletBalance();
           float amt = walletTopUpRequest.getAmt();
           bal = bal + amt;
           wallet.setWalletBalance(bal);
           walletRepository.save(wallet);
           return "added";

    }



}
