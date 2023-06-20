package com.example.Jammyhomie.repository;
import com.example.Jammyhomie.model.Cart;
import com.example.Jammyhomie.model.Jammyhomie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface JammyhomieRepository extends JpaRepository<Jammyhomie,Long>  {
    List<Jammyhomie> findByCategory(String category);
}



