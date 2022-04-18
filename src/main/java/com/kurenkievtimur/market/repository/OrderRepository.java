package com.kurenkievtimur.market.repository;

import com.kurenkievtimur.market.model.Order;
import com.kurenkievtimur.market.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(User user);

    Optional<Order> findByProductId(int id);
}
