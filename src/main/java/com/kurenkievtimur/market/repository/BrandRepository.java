package com.kurenkievtimur.market.repository;

import com.kurenkievtimur.market.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Optional<Brand> findFirstByNameIgnoreCase(String name);

}
