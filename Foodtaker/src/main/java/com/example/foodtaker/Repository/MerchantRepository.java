package com.example.foodtaker.Repository;

import com.example.foodtaker.Entity.MerchantDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantRepository extends JpaRepository<MerchantDetail, Integer> {

    List<MerchantDetail> findByName(String name);
    List<MerchantDetail> findByEmail(String email);
}
