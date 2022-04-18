package com.kurenkievtimur.market.service;

import com.kurenkievtimur.market.DTO.CreateUserDTO;
import com.kurenkievtimur.market.model.Product;

import java.util.List;

public interface UserService {
    void save(CreateUserDTO userDTO);
}
