package com.peopleplace.cafe.service;

import com.peopleplace.cafe.model.Category;
import com.peopleplace.cafe.repository.Categoryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private final Categoryrepository categoryrepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public CategoryService(Categoryrepository categoryrepository) {
        this.categoryrepository = categoryrepository;
    }

    public List<Category> listCategories() {
        return categoryrepository.findAll();
    }

    public void createCategory(Category category) {
        category.setId(sequenceGeneratorService.generateSequence(Category.SEQUENCE_NAME));
        categoryrepository.save(category);
    }

    public Category readCategory(String categoryName) {
        return categoryrepository.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Long categoryId) {
        return categoryrepository.findById(categoryId);
    }

    public void updateCategory(Long categoryID, Category newCategory) {
        Optional<Category> o = categoryrepository.findById(categoryID);
        if (o.isPresent()) {
            Category category = o.get();
            category.setCategoryName(newCategory.getCategoryName());
            category.setDescription(newCategory.getDescription());
            category.setProducts(newCategory.getProducts());
            category.setImageUrl(newCategory.getImageUrl());
            categoryrepository.save(category);
        }
    }
}