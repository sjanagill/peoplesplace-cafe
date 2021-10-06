package com.peopleplace.cafe.repository;

import com.peopleplace.cafe.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Categoryrepository extends MongoRepository<Category, Long> {

    Category findByCategoryName(String categoryName);

}