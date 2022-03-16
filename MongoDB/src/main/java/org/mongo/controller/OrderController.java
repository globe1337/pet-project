package org.mongo.controller;

import org.mongo.api.OrderRepository;
import org.mongo.entity.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public Order insert(@RequestBody Order order){
        return orderRepository.insert(order);
    }

    @DeleteMapping
    public void delete(@RequestBody Order order){
        orderRepository.delete(order);
    }

    @PutMapping
    public Order update(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @GetMapping
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    @GetMapping("/employee")
    public List<Order> findByEmployeeFirstName(@RequestParam String firstName){
        return orderRepository.findOrderByEmployee_FirstName(firstName);
    }
}
