package com.example.orders.inventory;

import com.example.orders.response.Response;
import com.example.orders.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {


    @Autowired
    InventoryService inventoryService;
    @Autowired
    WalletService walletService;

    @RequestMapping("/inventory")
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventories();


    }

    @RequestMapping(method = RequestMethod.POST, value = "/inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public String addInventory(@RequestBody List<Inventory> inventory)
    {

          return  inventoryService.addInventory(inventory);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/inventory/{id}/stock")
    public Long getAvailStock(@PathVariable Long id) {
        return inventoryService.getAvailStock(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/inventory/{id}/delete")
    public String deleteInventory(@PathVariable Long id) {
        return inventoryService.deleteInventory(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/inventory/{id}/price")
    public Float getAvailPrice(@PathVariable Long id) {
        return inventoryService.getAvailPrice(id);
    }

}
