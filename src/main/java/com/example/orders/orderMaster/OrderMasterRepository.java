package com.example.orders.orderMaster;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderMasterRepository extends CrudRepository<OrderMaster, Long> {

    void findAllByUserId(Long id);

//
}