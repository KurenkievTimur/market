package com.kurenkievtimur.market.service;

import com.kurenkievtimur.market.DTO.CreateBrandDTO;
import com.kurenkievtimur.market.model.Brand;
import com.kurenkievtimur.market.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public void save(CreateBrandDTO brandDTO) {
        Brand brand = Brand.builder()
                .name(brandDTO.getBrand().toLowerCase())
                .build();
        brandRepository.save(brand);
    }

    @Override
    public Optional<Brand> findById(int id) {
        return brandRepository.findById(id);
    }

    @Override
    public Optional<Brand> findFirstByNameIgnoreCase(String name) {
        return brandRepository.findFirstByNameIgnoreCase(name);
    }


    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
