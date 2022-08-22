package com.example.orders.user.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(method = RequestMethod.GET, value = "/address")
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();



    }

    @RequestMapping(method = RequestMethod.POST, value = "/address/{userId}")
    public ResponseEntity addAddress(@RequestBody AddressCreateRequest addressCreateRequest, @PathVariable Long userId) {
        addressService.addAddress(addressCreateRequest, userId);
        return ResponseEntity.ok().build();
    }

}
