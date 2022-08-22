package com.example.orders.wallet;

import com.example.orders.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {
    @Autowired
    WalletService walletService;

    @RequestMapping("/wallets")
    public List<Wallet> getAllWallets() {
        return walletService.getAllWallets();


    }


//    @RequestMapping(method = RequestMethod.POST, value = "wallets")
//    public void addWallet(@RequestBody WalletCreateRequest walletCreateRequest) {
//
//        walletService.addWallet(walletCreateRequest);
//
//    }
    @RequestMapping(method = RequestMethod.GET,value="/users/{id}/balance")
    public Float getBalance(@PathVariable Long id) {
        return walletService.getWalletBalance(id);
    }
    @RequestMapping(method = RequestMethod.PATCH,value="/users/topUp")
    public String updateWallet(@RequestBody WalletTopUpRequest walletTopUpRequest)
    {
        return walletService.topUpWallet(walletTopUpRequest);
    }

}
