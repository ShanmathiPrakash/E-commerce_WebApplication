package com.example.Jammyhomie.model;
import lombok.Data;
import javax.persistence.*;
import java.math.BigInteger;
@Data
@Entity
@Table(name="CustomerDetails")

public class CustomerDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long billId;
    @Column
    String customerId;
    @Column
    String customerName;
    @Column
    BigInteger total;
    @Column
    String status;
    @Column
    BigInteger phone;
    @Column
    BigInteger wallet;
    @Column
    BigInteger upiCardId;
    @Column
    BigInteger debitCardId;
    @Column
    BigInteger creditCardId;
    @Column
    BigInteger accountNo;
    @Column
    String address;
    @Column
    String email;

}
