package com.kurenkievtimur.market.service;

import com.kurenkievtimur.market.DTO.CreateProductDTO;
import com.kurenkievtimur.market.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByCategoryName(String name, Pageable pageable);

    Page<Product> findAllByCategoryNameAndBrandName(String categoryName, String brandName, Pageable pageable);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    List<Product> findAll();

    void save(CreateProductDTO productDTO);
}
