package br.com.srm.srmcurrencyconverter.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {


    @GetMapping("/products/list-products")
    public String getProduct() {

        return "product";
    }
}
