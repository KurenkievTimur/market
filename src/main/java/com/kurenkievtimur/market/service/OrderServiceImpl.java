package com.kurenkievtimur.market.service;

import com.kurenkievtimur.market.DTO.CreateOrderDTO;
import com.kurenkievtimur.market.model.Order;
import com.kurenkievtimur.market.model.User;
import com.kurenkievtimur.market.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void saveOrUpdate(CreateOrderDTO orderDTO, User user) {
        Optional<Order> optionalOrder = orderRepository.findByProductId(orderDTO.getProduct().getId());

        if (optionalOrder.isPresent()) {
            optionalOrder.get().setCount(optionalOrder.get().getCount() + 1);
            orderRepository.save(optionalOrder.get());
        } else {
            Order order = Order.builder()
                    .user(user)
                    .product(orderDTO.getProduct())
                    .count(orderDTO.getCount())
                    .build();
            orderRepository.save(order);
        }
    }
}
