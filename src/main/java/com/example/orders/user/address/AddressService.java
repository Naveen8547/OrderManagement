package com.example.orders.user.address;

import com.example.orders.user.User;
import com.example.orders.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    public List<Address> getAllAddress() {

        List<Address> addresses = new ArrayList<>();
        addressRepository.findAll()
                .forEach(addresses::add);
        return addresses;
    }

    public void addAddress(AddressCreateRequest addressCreateRequest, Long userId) {
        Address address = new Address(userService.getUser(userId), addressCreateRequest.getHouseNo(), addressCreateRequest.getStreet(), addressCreateRequest.getPincode(), addressCreateRequest.getCity());
        addressRepository.save(address);
    }

    public Address getAddress(Long addressId) {
        return addressRepository.findById(addressId).get();
    }

    public User getUSer(Long addressId) {
        return addressRepository.findById(addressId).get().getUser();
    }

}
