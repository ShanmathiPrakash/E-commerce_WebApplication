package com.example.Jammyhomie.service;
import com.example.Jammyhomie.Exception.ProductNotFoundException;
import com.example.Jammyhomie.model.CustomerDetails;
import com.example.Jammyhomie.model.Cart;
import com.example.Jammyhomie.model.Jammyhomie;
import com.example.Jammyhomie.repository.BillingRepository;
import com.example.Jammyhomie.repository.CartRepository;
import com.example.Jammyhomie.repository.JammyhomieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    BillingRepository billingRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    JammyhomieRepository jammyhomieRepository;
    @Autowired
    TwilioService twilioService;

    @Override
    public String bill(String customerId,String method)
    {
        Optional<CustomerDetails> OptionalCustomerDetails = Optional.ofNullable(billingRepository.findByCustomerId(customerId));
        if (!OptionalCustomerDetails.isPresent() && OptionalCustomerDetails==null || OptionalCustomerDetails.get().getTotal().equals(BigInteger.valueOf(0)))
        {
            throw new  ProductNotFoundException("bad");
        }
        CustomerDetails customerDetails=OptionalCustomerDetails.get();
            if ((customerDetails.getWallet().compareTo(customerDetails.getTotal())) > 0)
            {
                customerDetails.setWallet(customerDetails.getWallet().subtract(customerDetails.getTotal()));
                customerDetails.setTotal(BigInteger.valueOf(0));
                billingRepository.save(customerDetails);
                return "Done";
            }
            else if(method.equalsIgnoreCase("cart"))
            {
                BigInteger remainingAmount=customerDetails.getTotal().subtract(customerDetails.getWallet());
                BigInteger remainingAmountWithPercentage =(BigInteger.valueOf(5).multiply(remainingAmount)).divide(BigInteger.valueOf(100));
                remainingAmount=remainingAmount.add(remainingAmountWithPercentage);
                BigInteger remainingAmountInCreditCard = customerDetails.getCreditCardId().subtract(remainingAmount);
                customerDetails.setCreditCardId(remainingAmountInCreditCard);
                customerDetails.setTotal(BigInteger.valueOf(0));
                customerDetails.setWallet(BigInteger.valueOf(0));
                billingRepository.save(customerDetails);
                return "done";
            }
            else if(method.equalsIgnoreCase("UPI"))
            {
                BigInteger remainingAmount=customerDetails.getTotal().subtract(customerDetails.getWallet());
                BigInteger remainingAmountWithPercentage =(BigInteger.valueOf(5).multiply(remainingAmount)).divide(BigInteger.valueOf(100));
                remainingAmount=remainingAmount.add(remainingAmountWithPercentage);
                BigInteger remainingAmountInCreditCard = customerDetails.getCreditCardId().subtract(remainingAmount);
                customerDetails.setCreditCardId(remainingAmountInCreditCard);
                customerDetails.setTotal(BigInteger.valueOf(0));
                customerDetails.setWallet(BigInteger.valueOf(0));
                billingRepository.save(customerDetails);
                return "done";
            }
        throw new ProductNotFoundException("Method Not Found");
    }
}

