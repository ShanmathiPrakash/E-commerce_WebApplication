package com.example.Jammyhomie.service;
import com.example.Jammyhomie.Exception.ProductNotFoundException;
import com.example.Jammyhomie.model.*;
import com.example.Jammyhomie.repository.BillingRepository;
import com.example.Jammyhomie.repository.JammyhomieRepository;
import com.example.Jammyhomie.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class JammyhomieServiceimpl implements  JammyhomieService {
    @Autowired
    JammyhomieRepository jammyhomieRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BillingRepository billingRepository;
    @Autowired
    TwilioService twilioService;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    Pdf pdf;

    @Override//1st Achievement
    public Jammyhomie accountCreation(Jammyhomie jammyhomie) {
        return jammyhomieRepository.save(jammyhomie);
    }

    @Override//2nd Achievement
    public Optional<List<Jammyhomie>> getAllProducts() {
        Optional<List<Jammyhomie>> myList = Optional.of(jammyhomieRepository.findAll());
        if (myList.get().isEmpty() || myList == null) {
            throw new ProductNotFoundException("No products available");
        }
        return myList;
    }

    @Override//3rd Achievemet
    public Jammyhomie getAllProductsById(Long id) {
        Optional<Jammyhomie> mydata = jammyhomieRepository.findById(id);
        if (mydata.isPresent()) {
            Jammyhomie jammyhomie = mydata.get();
            return jammyhomie;
        } else {
            throw new ProductNotFoundException("Id Not Found");
        }
    }

    @Override//4th Achievement
    public Optional<List<Jammyhomie>> getAllProductsByCategory(String category) {
        Optional<List<Jammyhomie>> myList = Optional.of(jammyhomieRepository.findByCategory(category));
        if (myList == null || myList.get().isEmpty()) {
            throw new ProductNotFoundException("No products available");
        }
        return myList;
    }
    @Override//8th Achievement
    public List<Jammyhomie> getlimitedDetails(int limit) {
        Optional<List<Jammyhomie>> mydata = Optional.of(jammyhomieRepository.findAll());
        if (mydata.isPresent()) {
            List<Jammyhomie> jammyhomie = mydata.get();
            List<Jammyhomie> limitedJammyhomies = jammyhomie.stream()
                    .limit(limit)
                    .collect(Collectors.toList());
            return limitedJammyhomies;
        } else
            throw new ProductNotFoundException("No products available here");

    }

    @Override//9th Achievement
    public List<Cart> limitedDatasfromCart(int limit) {
        Optional<List<Cart>> mydata = Optional.of(cartRepository.findAll());
        if (mydata.isPresent()) {
            List<Cart> cart = mydata.get();
            List<Cart> limitedJammyhomies = cart.stream()
                    .limit(limit)
                    .collect(Collectors.toList());
            return limitedJammyhomies;
        } else
            throw new ProductNotFoundException("No products available here");
    }

    @Override//10th Achievement
    public List<Jammyhomie> rangeOfDatas(BigInteger limit1, BigInteger limit2) {
        Optional< List<Jammyhomie> >mydata= Optional.of(jammyhomieRepository.findAll());
        if (mydata.isPresent()) {
           List<Jammyhomie> jammyhomie=mydata.get();
            List<Jammyhomie> ans = jammyhomie.stream()
                    .filter(object -> object.getRate().compareTo(limit1) >= 0 && object.getRate().compareTo(limit2) <= 0)
                    .collect(Collectors.toList());
            if(ans.isEmpty() ) {
                throw new ProductNotFoundException("No products available here");
            }
                return ans;
        } else
            throw new ProductNotFoundException("No products available here");
    }

    @Override//11th Achievement
    public List<Jammyhomie> getbyrateAndCategory(BigInteger startingRate, BigInteger endingRate, String category) {
        Optional<List<Jammyhomie>> myData = Optional.ofNullable(jammyhomieRepository.findByCategory(category));
        if (myData.isPresent()) {
            List<Jammyhomie> list = myData.get();
            List<Jammyhomie> ans = list.stream()
                    .filter(jammyhomie -> jammyhomie.getRate().compareTo(startingRate) >= 0 && jammyhomie.getRate().compareTo(endingRate) <= 0)
                    .collect(Collectors.toList());
            if (ans.isEmpty())
                throw new ProductNotFoundException("No Matching Result");
            else
                return ans;
        }
        throw new ProductNotFoundException("No products available here");
    }

    @Override//12th Achievement
    public Jammyhomie updateUsingPutmapping(Long id, BigInteger rate, BigInteger stack, String region_availability) {
        Optional<Jammyhomie> product = jammyhomieRepository.findById(id);
        if (product.isPresent()) {
            Jammyhomie jammyhomie = product.get();
            jammyhomie.setRate(rate);
            jammyhomie.setStack(stack);
            jammyhomie.setRegion_availability(region_availability);
            return jammyhomieRepository.save(jammyhomie);
        } else {
            throw new ProductNotFoundException("Id Not Found");
        }
    }

    @Override//13th Achievement
    public Jammyhomie updateDatasUsingPatch(Long id, BigInteger rate, BigInteger stack, String region_availability) {
        Optional<Jammyhomie> product = jammyhomieRepository.findById(id);
        if (product.isPresent()) {
            Jammyhomie jammyhomie = product.get();
            jammyhomie.setName(jammyhomie.getName());
            jammyhomie.setCategory(jammyhomie.getCategory());
            jammyhomie.setRegion_availability(region_availability);
            jammyhomie.setRate(rate);
            jammyhomie.setStack(stack);
            jammyhomie.setDescription(jammyhomie.getDescription());
            jammyhomie.setReview_count(jammyhomie.getReview_count());
            jammyhomie.setRatings(jammyhomie.getRatings());
            return jammyhomieRepository.save(jammyhomie);
        } else {
            throw new ProductNotFoundException("Id Not Found");
        }
    }
    @Override//14th Achievement
    public List<Jammyhomie> getProductsSortedByRate(String sort) {
        Optional<List<Jammyhomie>> Mylist = Optional.of(jammyhomieRepository.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getRate))
                        .collect(Collectors.toList());
                return ans;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getRate).reversed())
                        .collect(Collectors.toList());
                return ans;
            }
        }
        throw new ProductNotFoundException("Method Not Found");
    }
    @Override//15th Achievement
    public List<Jammyhomie> getProductsSortedByCategory(String sort) {
        Optional<List<Jammyhomie>> Mylist = Optional.of(jammyhomieRepository.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getCategory))
                        .collect(Collectors.toList());
                return ans;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getCategory).reversed())
                        .collect(Collectors.toList());
                return ans;
            }
        }
        throw new ProductNotFoundException("Method Not Found");
    }

    @Override//16
    public List<Jammyhomie> getProductsSortedByRatings(String sort) {
        Optional<List<Jammyhomie>> Mylist = Optional.of(jammyhomieRepository.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getRatings))
                        .collect(Collectors.toList());
                return ans;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getRatings).reversed())
                        .collect(Collectors.toList());
                return ans;
            }
        }
        throw new ProductNotFoundException("Method Not Found");
    }

    @Override//17
    public List<Jammyhomie> getProductsSortedByName(String sort) {
        Optional<List<Jammyhomie>> Mylist = Optional.of(jammyhomieRepository.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getName))
                        .collect(Collectors.toList());
                return ans;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getName).reversed())
                        .collect(Collectors.toList());
                return ans;
            }
        }
        throw new ProductNotFoundException("Method Not Found");
    }

    @Override//18
    public List<Jammyhomie> getSortedByreview_count(String sort) {
        Optional<List<Jammyhomie>> Mylist = Optional.of(jammyhomieRepository.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getReview_count))
                        .collect(Collectors.toList());
                return ans;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getReview_count).reversed())
                        .collect(Collectors.toList());
                return ans;
            }
        }
        throw new ProductNotFoundException("Method Not Found");
    }
    @Override  //19
    public String deleteItemFromTable(Long id) {
        Optional<Jammyhomie> Mydata = jammyhomieRepository.findById(id);
        if (Mydata.isPresent()) {
            jammyhomieRepository.deleteById(id);
            return "Item Deleted...Successfully...";
        }
        throw new ProductNotFoundException("Deletion Failed");
    }
    @Override
    public List<String> finalBilling(String customerId)
    {
        Optional<List<Cart>> OptionalcartItems = Optional.ofNullable(cartRepository.findByCustomerId(customerId));
        Optional<CustomerDetails> OptionalCustomerDetails = Optional.ofNullable(billingRepository.findByCustomerId(customerId));
        //Instance lists to store the output
        List<String> ans = new ArrayList<>();
        List<String> outofstock = new ArrayList<>();
        List<Cart> listOfcartItems = OptionalcartItems.get();
        CustomerDetails customerDetails1=new CustomerDetails();
        boolean purchasedAtleastOneProduct = false;
        int No = 0;
        BigInteger item_count=BigInteger.valueOf(0);
        if (listOfcartItems.isEmpty())
        {
            throw new ProductNotFoundException("Your Cart is empty, Add Atleast 1 product to place an order");
        }
        if (!OptionalCustomerDetails.isPresent())
        {
                throw new ProductNotFoundException("Before placing an Order,Kindly give your Contact details");
        }
        BigInteger totalAmount = BigInteger.valueOf(0);
            for (Cart respectiveCartItem : listOfcartItems)
            {
                Long productId = respectiveCartItem.getProductId();
                Optional<Jammyhomie> optionalJammyhomie = jammyhomieRepository.findById(productId);
                if(!optionalJammyhomie.isPresent() || optionalJammyhomie==null)
                {
                    outofstock.add(respectiveCartItem.getProductName() + " : out of stock");
                    continue;
                }
                Jammyhomie jammy = optionalJammyhomie.get();
                BigInteger stackInJammy = jammy.getStack();
                if (stackInJammy.compareTo(respectiveCartItem.getQuantity()) > 0)
                {
                    No++;
                    item_count=item_count.add(respectiveCartItem.getQuantity());
                    //finding gst
                    BigInteger rateIncludingGst = respectiveCartItem.getRate().multiply(BigInteger.valueOf(14));
                    rateIncludingGst = rateIncludingGst.divide(BigInteger.valueOf(100));
                    rateIncludingGst = respectiveCartItem.getRate().add(rateIncludingGst);
                    ans.add(No + " . " + jammy.getName() + " = " + rateIncludingGst+"("+(respectiveCartItem.getQuantity()+")"));
                    totalAmount= totalAmount.add(respectiveCartItem.getQuantity().multiply(rateIncludingGst));
                    //Reducing stack in MainTable
                    jammy.setStack(jammy.getStack().subtract(respectiveCartItem.getQuantity()));
                    jammyhomieRepository.save(jammy);
                    purchasedAtleastOneProduct = true;
                }
                else
                {
                    outofstock.add(jammy.getName() + " : out of stock");
                }
            }
            if(!ans.isEmpty())
            {
                twilioService.sendSms("+919994871374","Dear Customer, Congrats!..Your order is on the way...Thanks and Regards  -JammyHomie!!!."+pdf.shan);
            }

            if(!outofstock.isEmpty())
            {
                ans.add(outofstock.toString());
            }
            String out = outofstock.toString();
            //Applying discount and delivery charge
            if (purchasedAtleastOneProduct) {
                ans.add("BILLING Details:  ");
                ans.add("    PRICE(" +  item_count + " items)    =    " + totalAmount);
                BigInteger discount = (totalAmount.multiply(BigInteger.valueOf(10))).divide(BigInteger.valueOf(100));
                discount = discount.subtract(BigInteger.valueOf(80)); //delivery charge
                totalAmount = totalAmount.subtract(discount);
                ans.add("    Discount          =   -" + discount);
                ans.add("    TOTAL AMOUNT      =    " + totalAmount);
                ans.add(("Hi "+OptionalCustomerDetails.get().getCustomerName()+" ,Your Order Placed Successfully.....Happy Shopping"));
            OptionalCustomerDetails.get().setTotal(totalAmount);
            CustomerDetails customerDetails2=OptionalCustomerDetails.get();
            billingRepository.save(customerDetails2);
            }
              cartRepository.deleteAllInBatch(listOfcartItems);
            return ans;
        }
    @Override
    public List<Jammyhomie> sortUsingBothNameAndRating(String sort) {
        Optional<List<Jammyhomie>> Mylist = Optional.of(jammyhomieRepository.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getName).thenComparing(Jammyhomie::getRatings))
                        .collect(Collectors.toList());
                return ans;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Jammyhomie> sortedTable = Mylist.get();
                List<Jammyhomie> ans = sortedTable.stream()
                        .sorted(Comparator.comparing(Jammyhomie::getName).thenComparing(Jammyhomie::getRatings).reversed())
                        .collect(Collectors.toList());
                return ans;
            }
        }
        throw new ProductNotFoundException("Method Not Found");
    }
    /*
@Override
    public void sendReferralCode(String recipientEmail, String hostEmail) {
    Optional<List<CustomerDetails>> referralOptional = Optional.ofNullable(billingRepository.findByEmail(hostEmail));
    if (referralOptional.isPresent())
    {
        List<CustomerDetails> referralList = referralOptional.get();   //HOST EMAILGET FROM CUSTOMERINFO DATABASE
        if (!referralList.isEmpty())
        {
            CustomerDetails referral = referralList.get(0);
            Optional<List<CustomerDetails>> recipientCheckOptional = Optional.ofNullable(billingRepository.findByEmail(recipientEmail));
            if (recipientCheckOptional.isPresent() && !recipientCheckOptional.get().isEmpty())
            {
                throw new ProductNotFoundException("This email address is already signed into our website");
            }
            //  String referralCode = referral.getReferralCode();
            String customer = referral.getCustomerName();
            String subject = "Invitation: Create an Account with Referral Code";
            String messageText = "Dear Friend,\n\n"
                    + "I would like to invite you to create an account on our platform using my referral code. With this referral code, you will receive special benefits!\n\n"
                    //        + "Referral Code: " + referralCode + "\n\n"
                    + "To create an account, please visit our registration page: [Insert Registration Page URL]\n\n"
                    + "Thank you for using my referral code. Let me know if you have any questions!\n\n"
                    + "Best regards,\n"
                    + customer;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(recipientEmail);
            message.setSubject(subject);
            message.setText(messageText);
            javaMailSender.send(message);
        }
    }
}

     */
}
