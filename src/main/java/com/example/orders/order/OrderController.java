package com.example.orders.order;

import com.example.orders.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();


    }

    @RequestMapping(method = RequestMethod.GET, value = "/orders/{id}")
    public List<OrderUser> getOrdersByUser(@PathVariable Long id) {
        return orderService.getOrdersByUser(id);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/orders/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "orders/users/{userId}")
    public List<String> buyProduct(@RequestBody ItemOrderCreateRequest itemOrderCreateRequest, @PathVariable Long userId) {
        List<String> messages = orderService.buyProduct(userId, itemOrderCreateRequest);

        return messages;
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/orders/status/update")
    public List<Response> updateStatus(@RequestBody List<Long> idList) {
        return orderService.updateStatus(idList);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/users/{userId}/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }


}
