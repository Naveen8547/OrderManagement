package com.example.orders.discountSchemes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscountSchemesController {

    @Autowired
    DiscountSchemesService discountSchemesService;

    @RequestMapping("/discountSchemes")
    public List<DiscountSchemes> getAllDiscountSchemes() {
        return discountSchemesService.getAllDiscountSchemes();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/discountSchemes")
    public String addDiscountSchemes(@RequestBody List<DiscountSchemes> discountSchemes) {
        return discountSchemesService.addDiscountSchemes(discountSchemes);
    }
}
