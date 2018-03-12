package com.eshop.repository;

import com.eshop.model.Purchase;
import com.eshop.model.User;
import com.eshop.model.web.PurchaseResponce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{

    @Query("select new com.eshop.model.web.PurchaseResponce("+
            "p.id,"+
            "p.product,"+
            "p.boughtBy,"+
            "p.quantity,"+
            "p.sellDate,"+
            "p.endPrice,"+
            "p.priceWithoutDiscount) from Purchase p where p.boughtBy = :purchBuyer")
    List<PurchaseResponce> getByBoughtBy(@Param("purchBuyer") User user);

    @Query("select new com.eshop.model.web.PurchaseResponce("+
    "p.id,"+
    "p.product,"+
    "p.boughtBy,"+
    "p.quantity,"+
    "p.sellDate,"+
    "p.endPrice,"+
    "p.priceWithoutDiscount) from Purchase p where p.id = :purchId")
    PurchaseResponce findByIdOrderById(@Param("purchId") Integer id);


}