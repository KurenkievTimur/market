package com.kurenkievtimur.market.service;

import com.kurenkievtimur.market.DTO.CreateCategoryDTO;
import com.kurenkievtimur.market.model.Category;
import com.kurenkievtimur.market.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void save(CreateCategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getCategory().toLowerCase())
                .build();
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findFirstByNameIgnoreCase(String name) {
        return categoryRepository.findFirstByNameIgnoreCase(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
