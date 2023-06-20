package com.example.Jammyhomie.service;
import com.example.Jammyhomie.Exception.ProductNotFoundException;
import com.example.Jammyhomie.model.Cart;
import com.example.Jammyhomie.model.CustomerDetails;
import com.example.Jammyhomie.model.Jammyhomie;
import com.example.Jammyhomie.repository.BillingRepository;
import com.example.Jammyhomie.repository.CartRepository;
import com.example.Jammyhomie.repository.JammyhomieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
@Service
public class CartServiceImpl implements CartService {
   @Autowired
    JammyhomieRepository jammyhomieRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BillingRepository billingRepository;
    @Override//5th Achievement
    public String addtocart(Long id, String customerId) {
        Optional<Jammyhomie> MyData = jammyhomieRepository.findById(id);
        Optional<List<Cart>> optionalProductAvailability = Optional.ofNullable(cartRepository.findByCustomerId(customerId));
        List<Cart> productAvailability=optionalProductAvailability.get();
        Boolean ans=productAvailability.stream().anyMatch(a->a.getProductId().equals(id));
        if(!MyData.isPresent())
        {
            throw new ProductNotFoundException("Products Not Found");
        }
        else if(ans) {
            for (Cart cart : productAvailability) {
                if (cart.getProductId().equals(id))
                    cart.setQuantity(cart.getQuantity().add(BigInteger.valueOf(1)));
                cartRepository.save(cart);
            }
        }
        else {
            Jammyhomie jammyhomie = MyData.get();
            Cart cart = new Cart();
            cart.setProductId(jammyhomie.getId());
            cart.setProductName(jammyhomie.getName());
            cart.setCustomerId(customerId);
            cart.setCategory(jammyhomie.getCategory());
            cart.setRate(jammyhomie.getRate());
            cart.setStack(jammyhomie.getStack());
            cart.setRegion_availability((jammyhomie.getRegion_availability()));
            cart.setDescription(jammyhomie.getDescription());
            cart.setRatings(jammyhomie.getRatings());
            cart.setReview_count(jammyhomie.getReview_count());
            cart.setQuantity(BigInteger.valueOf(1));
            cartRepository.save(cart);
        }
        return "Product Added Successfully";
    }
    @Override//5+1 achievement
    public CustomerDetails addToCustomerDetailsTable(CustomerDetails customerDetails)
    {
        return billingRepository.save(customerDetails);
          }
    @Override//6th Achievement
    public Optional<List<Cart>> getfromCart() {
        Optional<List<Cart>> myList = Optional.of(cartRepository.findAll());
        if (myList == null || myList.get().isEmpty()) {
            throw new ProductNotFoundException("No products available");
        }
        return myList;
    }
    @Override//7th Achievement
    public Cart getfromCartId(Long cartId)
    {
        Optional<Cart> mydata = cartRepository.findById(cartId);
        if (mydata.isPresent())
        {
            Cart cart = mydata.get();
            return cart;
        }
        throw new ProductNotFoundException("No products available here");
    }
    @Override  //19
    public String deletecustomerDetails(Long id)
    {

        Optional<CustomerDetails> Mydata = billingRepository.findById(id);
        if (Mydata.isPresent())
        {
            billingRepository.deleteById(id);
            return "Item Deleted...Successfully...";
        }
        else
        throw new ProductNotFoundException("Deletion Failed");

    }
}
