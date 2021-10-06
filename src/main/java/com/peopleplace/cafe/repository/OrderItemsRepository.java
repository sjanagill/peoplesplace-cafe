package com.peopleplace.cafe.repository;

import com.peopleplace.cafe.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderItemsRepository extends MongoRepository<OrderItem, Long> {
}
