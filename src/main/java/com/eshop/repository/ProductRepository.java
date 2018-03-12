package com.eshop.repository;

import com.eshop.model.Category;
import com.eshop.model.Product;
import com.eshop.model.User;
import com.eshop.model.web.ProductResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select new com.eshop.model.web.ProductResponse(" +
            "pr.id," +
            "pr.displayedName," +
            "pr.description," +
            "pr.category," +
            "pr.price," +
            "pr.discount," +
            "pr.quantity) from Product pr where pr.displayedName = :name")
    List<ProductResponse> findByDisplayedName(@Param("name") String name);

    @Query("select new com.eshop.model.web.ProductResponse(" +
            "pr.id," +
            "pr.displayedName," +
            "pr.description," +
            "pr.category," +
            "pr.price," +
            "pr.discount," +
            "pr.quantity) from Product pr where pr.id = :id and pr.displayedName = :name")
    ProductResponse findByIdAndDisplayedName(@Param("id") Integer id, @Param("name") String name);

    @Query("select new com.eshop.model.web.ProductResponse(" +
            "pr.id," +
            "pr.displayedName," +
            "pr.description," +
            "pr.category," +
            "pr.price," +
            "pr.discount," +
            "pr.quantity) from Product pr where pr.seller = :user")
    List<ProductResponse> getBySeller(@Param("user") User user);

    @Query("select new com.eshop.model.web.ProductResponse(" +
            "pr.id," +
            "pr.displayedName," +
            "pr.description," +
            "pr.category," +
            "pr.price," +
            "pr.discount," +
            "pr.quantity) from Product pr")
    List<ProductResponse> findAllOrderById();

    @Query("select new com.eshop.model.web.ProductResponse(" +
            "pr.id," +
            "pr.displayedName," +
            "pr.description," +
            "pr.category," +
            "pr.price," +
            "pr.discount," +
            "pr.quantity) from Product pr")
    List<ProductResponse> findAllOrderById(Sort sort);

    /*@Query("select new com.eshop.model.web.ProductResponse(" +
            "pr.id,"+
            "pr.displayedName,"+
            "pr.description,"+
            "pr.category,"+
            "pr.price,"+
            "pr.discount,"+
            "pr.sellerResponse,"+
            "pr.quantity) from Product pr where pr.id = :id")
    ProductResponse findById(@Param("id") Integer id);*/

    @Query("select new com.eshop.model.web.ProductResponse(" +
            "pr.id," +
            "pr.displayedName," +
            "pr.description," +
            "pr.category," +
            "pr.price," +
            "pr.discount," +
            "pr.quantity) from Product pr")
    List<ProductResponse> findByPriceBetween(Double minPrice, Double maxPrice);

    @Query("select new com.eshop.model.web.ProductResponse(" +
            "pr.id," +
            "pr.displayedName," +
            "pr.description," +
            "pr.category," +
            "pr.price," +
            "pr.discount," +
            "pr.quantity) from Product pr where pr.category = :cat")
    List<ProductResponse> findByCategory(@Param("cat") Category category, Sort sort);

    @Query("select new com.eshop.model.web.ProductResponse(" +
            "pr.id," +
            "pr.displayedName," +
            "pr.description," +
            "pr.category," +
            "pr.price," +
            "pr.discount," +
            "pr.quantity) from Product pr where pr.category = :cat")
    List<ProductResponse> findByCategory(@Param("cat") Category category);

    @Query("select new com.eshop.model.web.ProductResponse(" +
            "pr.id," +
            "pr.displayedName," +
            "pr.description," +
            "pr.category," +
            "pr.price," +
            "pr.discount," +
            "pr.quantity) from Product pr where pr.displayedName = :name and pr.category = :cat")
    List<ProductResponse> findByDisplayedNameAndCategory(@Param("name") String name, @Param("cat") Category category, Sort sort);


}