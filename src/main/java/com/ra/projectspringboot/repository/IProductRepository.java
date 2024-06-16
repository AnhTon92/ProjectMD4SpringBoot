package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface IProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryId(Long categoryId);

    Page<Product> findAllByIsDeleteIsFalse(Pageable pageable);

    List<Product> findAllByProductNameContainsOrDescriptionContains(String productName, String description);

    @Query("select p from Product p join WishList wl on p.id = wl.product.id")
    Set<Product> listOfFeaturedProducts();

    @Query("select p from Product p order by p.createdAt desc")
    List<Product> findTop5ByCreatedAtOrderByDesc();

    @Query("select p from Product p join OrderDetail od on od.product.id = p.id")
    Set<Product> bestSellerProducts();

    boolean existsByCategoryId(Long id);

    boolean existsByProductName(String productName);

}
