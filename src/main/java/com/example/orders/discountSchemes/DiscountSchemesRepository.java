package com.example.orders.discountSchemes;

import org.springframework.data.repository.CrudRepository;

public interface DiscountSchemesRepository extends CrudRepository<DiscountSchemes, Long> {

    DiscountSchemes findByRange(Long range);
}
