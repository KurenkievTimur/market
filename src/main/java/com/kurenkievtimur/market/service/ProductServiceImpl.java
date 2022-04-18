package com.kurenkievtimur.market.service;

import com.kurenkievtimur.market.DTO.CreateProductDTO;
import com.kurenkievtimur.market.model.Category;
import com.kurenkievtimur.market.model.Product;
import com.kurenkievtimur.market.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllByCategoryName(String name, Pageable pageable) {
        return productRepository.findAllByCategoryName(name, pageable);
    }

    @Override
    public Page<Product> findAllByCategoryNameAndBrandName(String categoryName, String brandName, Pageable pageable) {
        return productRepository.findAllByCategoryNameAndBrandName(categoryName, brandName, pageable);
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(CreateProductDTO productDTO) {
        Product product = Product.builder()
                .brand(productDTO.getBrand())
                .category(productDTO.getCategory())
                .name(productDTO.getName())
                .image(productDTO.getImage().getOriginalFilename())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();

        productRepository.save(product);
    }
}
