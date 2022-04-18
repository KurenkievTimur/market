package com.kurenkievtimur.market.service;

import com.kurenkievtimur.market.DTO.CreateBrandDTO;
import com.kurenkievtimur.market.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    void save(CreateBrandDTO brandDTO);

    Optional<Brand> findById(int id);

    Optional<Brand> findFirstByNameIgnoreCase(String name);

    List<Brand> findAll();
}
