package com.kurenkievtimur.market.DTO;

import com.kurenkievtimur.market.model.Brand;
import com.kurenkievtimur.market.model.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class CreateProductDTO {
    private Brand brand;
    private Category category;
    private String name;
    private MultipartFile image;
    private String description;
    private Double price;
}
