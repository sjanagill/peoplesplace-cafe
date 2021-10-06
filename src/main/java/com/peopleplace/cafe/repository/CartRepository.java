package com.peopleplace.cafe.repository;

import com.peopleplace.cafe.model.Cart;
import com.peopleplace.cafe.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, Long> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

    List<Cart> deleteByUser(User user);

    void save(Optional<Cart> cart);

}
