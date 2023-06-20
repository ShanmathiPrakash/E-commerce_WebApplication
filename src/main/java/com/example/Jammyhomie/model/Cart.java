package com.example.Jammyhomie.model;
import lombok.Data;
import javax.persistence.*;
import java.math.BigInteger;
@Data
@Entity
@Table(name="Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long cartId;
    @Column
    Long productId;
    @Column
    String productName;
    @Column
    String customerId;
    @Column
    String category;
    @Column
    BigInteger rate;
    @Column
    BigInteger stack;
    @Column
    String region_availability;
    @Column
    String description;
    @Column
    BigInteger ratings;
    @Column
    BigInteger review_count;
    @Column
    BigInteger quantity;

}
