package com.kurenkievtimur.market.service;

import com.kurenkievtimur.market.DTO.CreateOrderDTO;
import com.kurenkievtimur.market.model.Order;
import com.kurenkievtimur.market.model.User;
import com.kurenkievtimur.market.security.UserPrincipal;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllByUser(User user);
    Optional<Order> findById(int id);
    void delete(Order order);
    void save(Order order);
    void saveOrUpdate(CreateOrderDTO orderDTO, User user);
}
