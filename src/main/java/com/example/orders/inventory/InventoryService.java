package com.example.orders.inventory;

import com.example.orders.exceptionHandling.EmptyInputException;
import com.example.orders.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventories() {

        List<Inventory> inventories = new ArrayList<>();
        inventoryRepository.findAll()
                .forEach(inventories::add);
        return inventories;
    }

    public String addInventory(List<Inventory> inventory) {

        for (Inventory inventory1 : inventory)
        {

            if(inventory1.getProductName().isEmpty()||inventory1.getBrand().isEmpty()||inventory1.getDescription().isEmpty()||inventory1.getModel().isEmpty()||inventory1.getRetailer().isEmpty())
            {
                throw new EmptyInputException();

            }
            else
            {
                inventoryRepository.save(inventory1);

            }

        }
        return "products added";

    }


    public Long getAvailStock(Long id)
    {
        return  inventoryRepository.findById(id).get().getStock();
    }


    public Float getAvailPrice(Long id) {


        return inventoryRepository.findById(id).get().getPrice();
    }

    public Float getDiscount(Long id) {
        return inventoryRepository.findById(id).get().getDiscount();
    }

    public void updateStock(Long id, Long qty) {
        Long stk = inventoryRepository.findById(id).get().getStock();
        Long nst = stk - qty;
        Inventory inventory = inventoryRepository.findById(id).get();
        inventory.setStock(nst);
        inventoryRepository.save(inventory);
    }

    public void returnStock(Long id, Long qty) {
        Long stk = inventoryRepository.findById(id).get().getStock();
        Long nst = stk + qty;
        Inventory inventory = inventoryRepository.findById(id).get();
        inventory.setStock(nst);
        inventoryRepository.save(inventory);
    }


    public Inventory getInventory(Long inventoryId) {
        return inventoryRepository.findById(inventoryId).get();
    }

    public String deleteInventory(Long id) {


            inventoryRepository.deleteById(id);
            return "deleted";

    }


}