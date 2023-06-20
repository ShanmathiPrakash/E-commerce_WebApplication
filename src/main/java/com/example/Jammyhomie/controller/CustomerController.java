package com.example.Jammyhomie.controller;
import com.example.Jammyhomie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CustomerController
{
    @Autowired
    CustomerService customerService;
    @GetMapping("/billing/{customerId}/{method}")
    public String bill(@PathVariable String customerId,@PathVariable String method)
    {
        return customerService.bill(customerId,method);
    }
}
