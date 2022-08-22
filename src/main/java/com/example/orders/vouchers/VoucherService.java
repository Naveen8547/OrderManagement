package com.example.orders.vouchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    public List<Voucher> getAllVouchers(){
        List<Voucher> vouchers=new ArrayList<>();
        voucherRepository.findAll()
                .forEach(vouchers::add);
        return vouchers;

    }

    public  String addVoucher(List<Voucher> voucher) {

        for(Voucher voucher1:voucher)
        try {
            Date currentSqlDate = new Date(System.currentTimeMillis());
            voucher1.setCreatedDate(currentSqlDate);
            voucherRepository.save(voucher1);

        } catch (DataIntegrityViolationException e) {
            {
                Voucher voucher2=voucherRepository.findByToken(voucher1.getToken());
                voucherRepository.deleteById(voucher2.getVoucherId());
                Date currentSqlDate = new Date(System.currentTimeMillis());
                voucher1.setCreatedDate(currentSqlDate);
                voucherRepository.save(voucher1);
            }

        }
        return "added";
    }
    public  String deleteVoucher(Long id)
    {

                voucherRepository.deleteById(id);
                return "deleted";

    }

    public  Long getVoucherId(String token)
    {
       try{
           return voucherRepository.findByToken(token).getVoucherId();
       }
       catch (NullPointerException e)
       {
           return Long.valueOf(0);
       }
    }


    public Float  getDiscount(String token)
    {




          if(token==null||token=="")
              return Float.valueOf(0);
          else return voucherRepository.findByToken(token).getDiscountPer();

    }
    public Date getExpiryDate(String token)
    {


               return  voucherRepository.findByToken(token).getExpiryDate();



    }
    public  int getLimit(String token) {

        return voucherRepository.findByToken(token).getUserCount();

    }
    public void reduceCount(String token)
    {
        Voucher voucher=voucherRepository.findByToken(token);
        Integer userCount=voucher.getUserCount();
        voucher.setUserCount(userCount-1);
        voucherRepository.save(voucher);
    }





}
