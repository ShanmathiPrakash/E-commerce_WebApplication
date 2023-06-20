package com.example.Jammyhomie.repository;

import com.example.Jammyhomie.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<CustomerDetails,Long>
{
    CustomerDetails findByCustomerId(String customerId);
    List<CustomerDetails> findByEmail(String email);
}
