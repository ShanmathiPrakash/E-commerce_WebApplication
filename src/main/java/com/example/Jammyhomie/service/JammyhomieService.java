package com.example.Jammyhomie.service;
import com.example.Jammyhomie.model.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
public interface JammyhomieService {
    Jammyhomie accountCreation(Jammyhomie jammyhomie);
    Optional<List<Jammyhomie>>getAllProducts();
    Jammyhomie getAllProductsById(Long id);
    Optional<List<Jammyhomie>> getAllProductsByCategory(String category);
    List<Jammyhomie> getlimitedDetails(int limit);
    List<Cart> limitedDatasfromCart(int limit);
    List<Jammyhomie> rangeOfDatas(BigInteger limit1,BigInteger limit2);
    List<Jammyhomie> getbyrateAndCategory(BigInteger startingRate,BigInteger endingRate,String category);
    Jammyhomie updateUsingPutmapping(Long id, BigInteger rate, BigInteger stack, String region_availability);
    Jammyhomie updateDatasUsingPatch (Long id, BigInteger rate, BigInteger stack, String region_availability);
    List<Jammyhomie> getProductsSortedByRate(String sort);
    List<Jammyhomie> getProductsSortedByCategory(String sort);
    List<Jammyhomie> getProductsSortedByRatings(String sort);
    List<Jammyhomie> getProductsSortedByName(String sort);
    List<Jammyhomie> getSortedByreview_count(String sort);
    String deleteItemFromTable(Long id);
    List<String> finalBilling(String customerId);
    List<Jammyhomie> sortUsingBothNameAndRating(String sort);

    //void sendReferralCode(String recipienteMail,String hostMail);

    //String deleteFromCartAndBillingTable(Long id);

}
