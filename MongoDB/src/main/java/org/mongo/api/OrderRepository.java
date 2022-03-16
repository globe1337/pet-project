package org.mongo.api;

import org.mongo.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findOrderByEmployee_FirstName(String firstName);
}
