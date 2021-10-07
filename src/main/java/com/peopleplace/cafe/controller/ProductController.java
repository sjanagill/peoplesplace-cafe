package com.peopleplace.cafe.controller;

import com.peopleplace.cafe.common.ApiResponse;
import com.peopleplace.cafe.dto.ProductDto;
import com.peopleplace.cafe.exceptions.AuthenticationFailException;
import com.peopleplace.cafe.exceptions.OrderNotFoundException;
import com.peopleplace.cafe.model.Category;
import com.peopleplace.cafe.model.Product;
import com.peopleplace.cafe.service.AuthenticationService;
import com.peopleplace.cafe.service.CategoryService;
import com.peopleplace.cafe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping(path = "/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto, @RequestHeader("token") String token) {
        authenticationService.authenticate(token);
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category, token);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto, @RequestHeader("token") String token) {
        authenticationService.authenticate(token);
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productID, productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") Long id) throws AuthenticationFailException {
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}