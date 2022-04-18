package com.kurenkievtimur.market.repository;

import com.kurenkievtimur.market.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByCategoryName(String name, Pageable pageable);

    Page<Product> findAllByCategoryNameAndBrandName(String categoryName, String brandName, Pageable pageable);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);
}
