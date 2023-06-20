package com.example.Jammyhomie.controller;

import com.example.Jammyhomie.model.Cart;
import com.example.Jammyhomie.model.CustomerDetails;
import com.example.Jammyhomie.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/achievement5/{id}/{customerId}")
    public String addtocart(@PathVariable Long id, @PathVariable String customerId)
    {
        return cartService.addtocart(id,customerId);
    }
    @PostMapping("/achievement5+1")
    public ResponseEntity<CustomerDetails> addToCustomerDetailsTable(@RequestBody CustomerDetails customerDetails)
    {
        return new ResponseEntity<CustomerDetails>(cartService.addToCustomerDetailsTable(customerDetails),HttpStatus.ACCEPTED);
    }
    @GetMapping("/achievement6")
    public ResponseEntity<Optional<List<Cart>>> getfromCart()
    {
        Optional<List<Cart>> cart = cartService.getfromCart();
        if (cart.get().isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
    }
    @GetMapping("/achievement7/{productid}")
    public ResponseEntity<Cart> getfromCartId(@PathVariable Long productid) {
        return new ResponseEntity<Cart>(cartService.getfromCartId(productid), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deletionINCustomerTable/{id}")
    public ResponseEntity<String> deletecustomerDetails(@PathVariable Long id) {
        return new ResponseEntity<String>(cartService.deletecustomerDetails(id), HttpStatus.ACCEPTED);

    }
}
