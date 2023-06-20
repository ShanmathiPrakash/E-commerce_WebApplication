package com.example.Jammyhomie.controller;
import com.example.Jammyhomie.model.*;
import com.example.Jammyhomie.service.JammyhomieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
@RestController
public class JammyhomieController {
    @Autowired
    JammyhomieService jammyhomieService;
    @PostMapping("/achievement1")
    public ResponseEntity<Jammyhomie> accountCreation(@RequestBody Jammyhomie jammyhomie) {
        return new ResponseEntity<Jammyhomie>(jammyhomieService.accountCreation(jammyhomie), HttpStatus.ACCEPTED);
    }
    @GetMapping("/achievement2")
    public ResponseEntity<Optional<List<Jammyhomie>>> getAllProducts() {
        return new ResponseEntity<Optional<List<Jammyhomie>>>(jammyhomieService.getAllProducts(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/achievement3/{id}")
    public ResponseEntity<Jammyhomie> getAllProductsById(@PathVariable Long id) {
        return new ResponseEntity<Jammyhomie>(jammyhomieService.getAllProductsById(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/achievement4/{category}")
    public ResponseEntity<Optional<List<Jammyhomie>>> getAllProductsByCategory(@PathVariable String category) {
        return new ResponseEntity<Optional<List<Jammyhomie>>>(jammyhomieService.getAllProductsByCategory(category), HttpStatus.ACCEPTED);
    }
    @GetMapping("/achievement8/{limit}")
    public ResponseEntity<List<Jammyhomie>> getlimitedDetails(@PathVariable int limit) {
        return new ResponseEntity<List<Jammyhomie>>(jammyhomieService.getlimitedDetails(limit), HttpStatus.ACCEPTED);
    }
    @GetMapping("/achievement9/{limit}")
    public ResponseEntity<List<Cart>> limitedDatasfromCart(@PathVariable int limit) {
        return new ResponseEntity<List<Cart>>(jammyhomieService.limitedDatasfromCart(limit), HttpStatus.ACCEPTED);
    }
    @GetMapping("/achievement10/{limit1}/{limit2}")
    public ResponseEntity<List<Jammyhomie>> rangeOfDatas(@PathVariable BigInteger limit1, @PathVariable BigInteger limit2) {
        return new ResponseEntity<List<Jammyhomie>>(jammyhomieService.rangeOfDatas(limit1, limit2), HttpStatus.ACCEPTED);
    }
    @GetMapping("/achievement11/{startingRate}/{endingRate}/{category}")
    public ResponseEntity<List<Jammyhomie>> getbyrateAndCategory(@PathVariable BigInteger startingRate, @PathVariable BigInteger endingRate, @PathVariable String category) {
        return new ResponseEntity<List<Jammyhomie>>(jammyhomieService.getbyrateAndCategory(startingRate, endingRate, category), HttpStatus.ACCEPTED);
    }
    @PutMapping("/achievement12/{id}/{rate}/{stack}/{region_availability}")
    public ResponseEntity<Jammyhomie> updateUsingPutmapping(@PathVariable Long id, @PathVariable BigInteger rate, @PathVariable BigInteger stack, @PathVariable String region_availability) {
        return new ResponseEntity<Jammyhomie>(jammyhomieService.updateUsingPutmapping(id, rate, stack, region_availability), HttpStatus.OK);
    }
    @PatchMapping("/achievement13/{id}/{rate}/{stack}/{region_availability}")
    public ResponseEntity<Jammyhomie> updateDatasUsingPatch(@PathVariable Long id, @PathVariable BigInteger rate, @PathVariable BigInteger stack, @PathVariable String region_availability)
    {
        return new ResponseEntity<Jammyhomie>(jammyhomieService.updateDatasUsingPatch(id, rate, stack, region_availability), HttpStatus.OK);
    }
    @GetMapping("/achievement14/SortUsingRate/{sort}")
    public ResponseEntity<List<Jammyhomie>> getProductsSortedByRate(@PathVariable String sort) {
        return new ResponseEntity<List<Jammyhomie>>(jammyhomieService.getProductsSortedByRate(sort), HttpStatus.OK);
    }
    @GetMapping("/achievement15/SortUsingCategory/{sort}")
    public List<Jammyhomie> getProductsSortedByCategory(@PathVariable String sort) {
        return jammyhomieService.getProductsSortedByCategory(sort);
    }
    @GetMapping("/achievement16/SortUsingRatings/{sort}")
    public List<Jammyhomie> getProductsSortedByRatings(@PathVariable String sort)
    {
        return jammyhomieService.getProductsSortedByRatings(sort);
    }
    @GetMapping("/achievement17/SortUsingName/{sort}")
    public List<Jammyhomie> getProductsSortedByName(@PathVariable String sort)
    {
        return jammyhomieService.getProductsSortedByName(sort);
    }
    @GetMapping("/achievement18/SortUsingReview_count/{sort}")
    public List<Jammyhomie> getProductsSortedByreview_count(@PathVariable String sort)
    {
        return jammyhomieService.getSortedByreview_count(sort);
    }
    @DeleteMapping("/achievement19/{id}")
    public ResponseEntity<String> deleteItemFromTable(@PathVariable Long id)
    {
        return new ResponseEntity<String>(jammyhomieService.deleteItemFromTable(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/achievement20/{customerId}")
    public ResponseEntity<List<String>> finalBilling(@PathVariable String customerId)
    {
        return new ResponseEntity<List<String>>(jammyhomieService.finalBilling(customerId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/achievement21/{sort}")
    public List<Jammyhomie> sortUsingBothNameAndRating(@PathVariable String sort)
    {
        return jammyhomieService.sortUsingBothNameAndRating(sort);
    }
    }
