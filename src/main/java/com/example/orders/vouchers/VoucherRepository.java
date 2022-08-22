package com.example.orders.vouchers;

import org.springframework.data.repository.CrudRepository;

public interface VoucherRepository extends CrudRepository<Voucher,Long> {
    public  Voucher findByToken(String voucherToken);
}
