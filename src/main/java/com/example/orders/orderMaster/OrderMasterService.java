package com.example.orders.orderMaster;

import com.example.orders.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderMasterService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private UserService userService;

    public List<OrderMaster> getAllOrders() {

        List<OrderMaster> orders = new ArrayList<>();
        orderMasterRepository.findAll()
                .forEach(orders::add);
        return orders;
    }

    public List<Long> getMasterOrderByUser(Long id) {

        List<Long> masterList = new ArrayList<>();
        List<OrderMaster> masterOrders = new ArrayList<>();
        orderMasterRepository.findAll()
                .forEach(masterOrders::add);
        for (OrderMaster orderMaster : masterOrders)
            if (orderMaster.getUser() == userService.getUser(id))
                masterList.add(orderMaster.getMasterOrderId());


        return masterList;
    }

    public void addOrderMaster(OrderMaster orderMaster) {
        orderMasterRepository.save(orderMaster);
    }


    public void updateStatus(Long id) {
        OrderMaster orderMaster = orderMasterRepository.findById(id).get();
        orderMaster.setStatus("Delivered");
        orderMasterRepository.save(orderMaster);

    }

    public void cancelOrder(Long id) {
        OrderMaster orderMaster = orderMasterRepository.findById(id).get();
        orderMaster.setStatus("cancelled");
        orderMasterRepository.save(orderMaster);

    }

    public void updateSubTotal(Long id, Float total, Float dis) {
        OrderMaster orderMaster = orderMasterRepository.findById(id).get();
        total = orderMaster.getSubTotal() - total;
        dis = orderMaster.getDiscount() - dis;
        orderMaster.setSubTotal(total);
        orderMaster.setDiscount(dis);
        orderMasterRepository.save(orderMaster);
    }

}


