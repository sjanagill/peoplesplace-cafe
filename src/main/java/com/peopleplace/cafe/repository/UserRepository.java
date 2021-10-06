package com.peopleplace.cafe.repository;

import com.peopleplace.cafe.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    @NotNull List<User> findAll();

    User findByEmail(String email);

    User findUserByEmail(String email);
}