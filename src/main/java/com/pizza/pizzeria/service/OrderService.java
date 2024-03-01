package com.pizza.pizzeria.service;

import com.pizza.pizzeria.persistence.entity.OrderEntity;
import com.pizza.pizzeria.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<OrderEntity> getAll(){
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(or -> System.out.println(or.getCustomer().getName()));
        return orders;
    }
}
