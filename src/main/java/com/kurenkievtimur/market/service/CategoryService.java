package com.kurenkievtimur.market.service;

import com.kurenkievtimur.market.DTO.CreateCategoryDTO;
import com.kurenkievtimur.market.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void save(CreateCategoryDTO categoryDTO);

    Optional<Category> findById(int id);

    Optional<Category> findFirstByNameIgnoreCase(String name);

    List<Category> findAll();
}
