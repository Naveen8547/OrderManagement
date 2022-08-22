package com.example.orders.vouchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoucherController {

    @Autowired
    VoucherService voucherService;

    @RequestMapping("/vouchers")
    public List<Voucher> getAllVouchers()
    {
        return voucherService.getAllVouchers();
    }

    @RequestMapping(method= RequestMethod.POST,value="/vouchers")
    public String addVoucher(@RequestBody List<Voucher> voucher)
    {
        return  voucherService.addVoucher(voucher);
    }

    @RequestMapping(method =RequestMethod.DELETE,value="/vouchers/{id}")
    public String deleteVoucher(@PathVariable Long id){
        return  voucherService.deleteVoucher(id);
    }



}
