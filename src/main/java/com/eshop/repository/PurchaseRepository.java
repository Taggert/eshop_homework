package com.eshop.repository;

import com.eshop.model.Purchase;
import com.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{

    List<Purchase> getByBoughtBy(User user);


}