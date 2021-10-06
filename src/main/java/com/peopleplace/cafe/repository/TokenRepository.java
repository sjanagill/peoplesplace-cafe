package com.peopleplace.cafe.repository;


import com.peopleplace.cafe.model.AuthenticationToken;
import com.peopleplace.cafe.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<AuthenticationToken, Long> {
    AuthenticationToken findTokenByUser(User user);

    AuthenticationToken findTokenByToken(String token);
}
