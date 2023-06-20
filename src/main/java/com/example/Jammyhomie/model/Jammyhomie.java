package com.example.Jammyhomie.model;
import lombok.Data;
import javax.persistence.*;
import java.math.BigInteger;
@Data
@Entity
@Table(name="Jammyhomie")
public class Jammyhomie {
@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
@Column(nullable = false)
    String name;
@Column(nullable = false)
    String category;
@Column(nullable = false)
    BigInteger rate;
@Column(nullable = false)
    BigInteger stack;
@Column(nullable = false)
    String region_availability;
@Column(nullable = false)
    String description;
@Column(nullable = false)
    BigInteger ratings;
@Column(nullable = false)
    BigInteger review_count;
}
