package com.example.orders.order;

import com.example.orders.discountSchemes.DiscountSchemesService;
import com.example.orders.inventory.Inventory;
import com.example.orders.inventory.InventoryService;
import com.example.orders.orderMaster.OrderMaster;
import com.example.orders.orderMaster.OrderMasterService;
import com.example.orders.response.Response;
import com.example.orders.user.User;
import com.example.orders.user.UserService;
import com.example.orders.user.address.AddressService;
import com.example.orders.vouchers.VoucherService;
import com.example.orders.wallet.Wallet;
import com.example.orders.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    @Autowired
    VoucherService voucherService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private DiscountSchemesService discountSchemesService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderMasterService orderMasterService;

    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();
        orderRepository.findAll()
                .forEach(orders::add);
        return orders;
    }

    public void addOrder(OrderMaster orderMaster, Long inventoryId, Long qty, Float price, Float discountAmt) {

        Order order = new Order(orderMaster, inventoryId, qty, price, "Not Delivered", discountAmt);
        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }


    @Transactional
    public List<String> buyProduct(Long userId, ItemOrderCreateRequest itemOrderCreateRequest) {
        Float totalDiscount = Float.valueOf(0L);
        Float totalCost = Float.valueOf(0L);
        Date currentSqlDate = new Date(System.currentTimeMillis());
        List<OrderTemp> ordersList = new ArrayList<>();
        Wallet wallet = walletService.getWalletByUserId(userId);

        List<String> messages = new ArrayList<>();

        try {
            if (voucherService.getVoucherId(itemOrderCreateRequest.getToken()) == 0)
                messages.add("Voucher doesn't exist");
            else if (voucherService.getExpiryDate(itemOrderCreateRequest.getToken()).compareTo(currentSqlDate) <= 0) {
                messages.add("voucher expired");
            } else if (voucherService.getLimit(itemOrderCreateRequest.getToken()) == 0) {
                messages.add("voucher limit reached");
            } else if (addressService.getUSer(itemOrderCreateRequest.getAddressId()).getId() == userId) {
                for (int i = 0; i < itemOrderCreateRequest.getOrderCreateRequests().size(); i++) {
                    Long inventoryId = itemOrderCreateRequest.getOrderCreateRequests().get(i).getInventoryId();
                    Long qty = itemOrderCreateRequest.getOrderCreateRequests().get(i).getQty();

                    Float bal = wallet.getWalletBalance();

                    Inventory inventory = inventoryService.getInventory(inventoryId);
                    Long stk = inventory.getStock();
                    Float price = inventory.getPrice() * qty;
                    Float discountPer = inventoryService.getDiscount(inventoryId);
                    totalDiscount = totalDiscount + ((price * discountPer) / 100);
                    Float cost = price * (100 - discountPer) / 100;
                    totalCost = totalCost + cost;
                    Float inDiscount = (price * discountPer) / 100;


                    if (stk < qty) {
                        messages.add(String.format("%s is out of stock", inventory.getProductName()));
                    } else if (bal >= cost) {
                        walletService.updateWallet(wallet.getWalletId(), cost);
                        inventoryService.updateStock(inventoryId, qty);

                        OrderTemp orderTemp = new OrderTemp(inventoryId, qty, cost, "Not delivered", inDiscount);
                        ordersList.add(orderTemp);
                        messages.add(String.format("order is placed for %s ", inventory.getProductName()));
                    } else {
                        messages.add(String.format("Not enough balance for %s ", inventory.getProductName()));
                    }

                    List<Order> orders = new ArrayList<>();


                }
                Float dsmt = voucherService.getDiscount(itemOrderCreateRequest.getToken());

                Float dsmtFinal = (totalCost * dsmt) / 100;
                totalCost = totalCost * (100 - dsmt) / 100;
                voucherService.reduceCount(itemOrderCreateRequest.getToken());

                Float i = discountSchemesService.applyDiscountScheme(totalCost);

                Float dsmtLast = (totalCost * i) / 100;
                totalCost = totalCost * (100 - i) / 100;

                totalDiscount = totalDiscount + dsmtFinal + dsmtLast;


                walletService.reWallet(wallet.getWalletId(), dsmtFinal + dsmtLast);
                OrderMaster orderMaster = new OrderMaster(userService.getUser(userId), addressService.getAddress(itemOrderCreateRequest.getAddressId()), currentSqlDate, totalCost, totalDiscount, "Not Delivered");
                orderMasterService.addOrderMaster(orderMaster);
                for (OrderTemp orderTemps : ordersList)
                    addOrder(orderMaster, orderTemps.getProductId(), orderTemps.getQty(), orderTemps.getPrice(), orderTemps.getDiscount());
                messages.add("completed");
            } else {
                messages.add("user doesn't have the  address");
            }
        } catch (NoSuchElementException e) {
            messages.add("user doesn't have the specified address");
        }
        return messages;
    }

    public List<OrderUser> getOrdersByUser(Long id) {
        List<Long> masterOrders = new ArrayList<>();
        List<OrderUser> orderUserList = new ArrayList<>();

        List<Order> orders = new ArrayList<>();
        orderRepository.findAll()
                .forEach(orders::add);


        masterOrders = orderMasterService.getMasterOrderByUser(id);
        for (Long masterOrder1 : masterOrders) {
            for (Order order : orders) {
                if (order.getOrderMaster().getMasterOrderId() == masterOrder1) {
                    OrderUser orderUser = new OrderUser(masterOrder1, order.getOrderId(), order.getInventory(), order.getQty(), order.getStatus());
                    orderUserList.add(orderUser);
                }
            }
        }

        return orderUserList;

    }

    @Transactional
    public List<Response> updateStatus(List<Long> idList) {
        int i;


        List<Response> responseList = new ArrayList<>();


        for (Long id : idList) {
            i = 0;
            try {
                Order order = orderRepository.findById(id).get();
                order.setStatus("delivered");
                orderRepository.save(order);

                List<Order> ordersList = new ArrayList<>();
                for (Order order1 : orderRepository.findAll()) {
                    if (order1.getOrderMaster().getMasterOrderId() == order.getOrderMaster().getMasterOrderId()) {
                        ordersList.add(order1);
                    }
                }
                for (Order order1 : ordersList) {
                    if (order1.getStatus().compareTo("Not Delivered") == 0) {
                        i = 1;
                        break;
                    }
                }
                Response response = new Response("updated", order.getOrderId());
                responseList.add(response);
            } catch (NoSuchElementException e) {
                i = 1;
                Response response = new Response("No such id", id);
                responseList.add(response);
            }
            if (i == 0) {
                orderMasterService.updateStatus(orderRepository.findById(id).get().getOrderMaster().getMasterOrderId());
            }


        }
        return responseList;

    }

   @Transactional
    public String cancelOrder(Long orderId) {
        int i = 0;
        Float tt = Float.valueOf(0L);
        try {
            Order order = orderRepository.findById(orderId).get();
            if (order.getStatus().compareTo("delivered") == 0)
                return "Order Already delivered";
            else if (order.getStatus().compareTo("cancelled") == 0) {
                return "order already cancelled";
            } else {
                List<Order> ordersList = new ArrayList<>();
                for (Order order1 : orderRepository.findAll()) {
                    if (order1.getOrderMaster().getMasterOrderId() == order.getOrderMaster().getMasterOrderId()) {
                        ordersList.add(order1);
                        tt = tt + (order1.getDiscount() * order1.getQty());
                    }
                }
                if (tt < order.getOrderMaster().getDiscount())
                    return "Orders that have vouchers applied cannot be cancelled";
                else {

                    order.setStatus("cancelled");
                    orderRepository.save(order);
                    User user = order.getOrderMaster().getUser();
                    Inventory inventory = inventoryService.getInventory(order.getInventory());
                    inventoryService.returnStock(inventory.getProductId(), order.getQty());
                    Float rtnamt = order.getQty() * order.getPrice();
                    walletService.reWallet(walletService.getWalletByUserId(user.getId()).getWalletId(), rtnamt);
                    orderMasterService.updateSubTotal(order.getOrderMaster().getMasterOrderId(), rtnamt, order.getDiscount() * order.getQty());

                    // List<Order> ordersList=new ArrayList<>();
                    for (Order order1 : orderRepository.findAll()) {
                        if (order1.getOrderMaster().getMasterOrderId() == order.getOrderMaster().getMasterOrderId()) {
                            ordersList.add(order1);
                        }
                    }
                    for (Order order1 : ordersList) {
                        if (order1.getStatus().compareTo("cancelled") != 0) {
                            i = 1;
                            break;
                        }
                    }
                    if (i == 0) {
                        orderMasterService.cancelOrder(order.getOrderMaster().getMasterOrderId());
                    }
                    return "order cancelled";
                }
            }
        } catch (NoSuchElementException e) {
            return "no such order";
        }
    }


}



