package com.example.orders.discountSchemes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DiscountSchemesService {

    @Autowired
    private DiscountSchemesRepository discountSchemesRepository;

    public List<DiscountSchemes> getAllDiscountSchemes() {

        List<DiscountSchemes> discountSchemes = new ArrayList<>();
        discountSchemesRepository.findAll()
                .forEach(discountSchemes::add);
        return discountSchemes;
    }

    public String addDiscountSchemes(List<DiscountSchemes> discountSchemes) {

        for (DiscountSchemes discount : discountSchemes)
            try {

                discountSchemesRepository.save(discount);
            } catch (DataIntegrityViolationException e) {
                DiscountSchemes discountSchemes1 = discountSchemesRepository.findByRange(discount.getRange());
                discountSchemesRepository.deleteById(discountSchemes1.getSchemeId());
                discountSchemesRepository.save(discount);

            }
        return "added";
    }

    public Float applyDiscountScheme(Float totalCost) {


        List<Float> discounts = new ArrayList<>();
        List<DiscountSchemes> discountSchemesList = new ArrayList<>();
        discountSchemesRepository.findAll().forEach(discountSchemesList::add);
        for (DiscountSchemes discountSchemes : discountSchemesList) {
            if (totalCost > discountSchemes.getRange()) {
                discounts.add(discountSchemes.getSchemePer());
            }

        }

        try {
            return Collections.max(discounts);
        } catch (
                NoSuchElementException e) {
            return Float.valueOf(0);
        }

    }
}