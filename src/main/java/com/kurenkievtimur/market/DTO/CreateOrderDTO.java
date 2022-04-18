package com.kurenkievtimur.market.DTO;

import com.kurenkievtimur.market.model.Product;
import lombok.Data;

@Data
public class CreateOrderDTO {
    private Product product;
    private Integer count;
}
