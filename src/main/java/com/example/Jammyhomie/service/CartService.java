package com.example.Jammyhomie.service;

import com.example.Jammyhomie.model.Cart;
import com.example.Jammyhomie.model.CustomerDetails;

import java.util.List;
import java.util.Optional;

public interface CartService
{
    String addtocart(Long id,String customerId);
    CustomerDetails addToCustomerDetailsTable(CustomerDetails customerDetails);
    Optional<List<Cart>> getfromCart();
    Cart getfromCartId(Long productid);
    String deletecustomerDetails(Long id);

}
