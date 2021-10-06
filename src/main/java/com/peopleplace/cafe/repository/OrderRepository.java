package com.peopleplace.cafe.repository;

import com.peopleplace.cafe.model.Order;
import com.peopleplace.cafe.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);

}
